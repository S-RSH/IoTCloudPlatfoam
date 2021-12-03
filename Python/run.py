from gpiozero import Button
import cv2
import time
import datetime
import boto3
import json

def publish_multi_message(topic, subject, default_message, fcm_message): # 다형식의 전송 프로토콜로 메시지 전송

    message = {
        'default': default_message,
        'GCM': fcm_message
    } # 전달할 메시지의 형식 설정
    response = topic.publish(
        Message=json.dumps(message), Subject=subject, MessageStructure='json') # 위에서 생성한 형식을 json의 형태로 변경
    message_id = response['MessageId'] # 서버로 메시지를 전송
    return message_id

if __name__ == '__main__':

    # Let's use Amazon Web Service
    s3 = boto3.client('s3') # S3
    dynamodb = boto3.resource('dynamodb') # Dynamodb
    table = dynamodb.Table('doorbell') # Dynamodb -> table
    cap = cv2.VideoCapture(0) # Video capture
    button = Button(2) # button
    sns = boto3.resource("sns") # Amzon SNS
    topic = sns.Topic(arn="arn:aws:sns:ap-northeast-2:xxxxxxxxxxxx:Doorbell") # 메시지를 게시할 주제 선택 *개인의 arn에 맞게 변경*
    fcmmessage = "{ \"notification\": { \"title\": \"Dingdong\", \"body\": \"somebody is at the front door!\" }}" # json 파일로 변환해 전달 할 str문자열
    bucket = 'lambda-function-bucket-ap-northeast-2-xxxxxxxxxxxxx' # 사용할 s3 bucket *개인의 버킷에 맞게 변경*
    folder = 'picture/' # s3 bucket 내부에서 사용할 폴더 *적용할 폴더명 변경시 변경*

    if not cap.isOpened():
        print('[!] video open failed!') # opencv 카메라 연결 여부 확인

    while True:
        ret, frame = cap.read() # 매 반복마다 카메라에서 이미지 영상 읽기
        if not ret: # 이미지를 못 읽으면 중지
            break

        cv2.imshow('frame',frame) # 이미지 보여주기

        if cv2.waitKey(30) == 27: # esc키 사용시 중지
            break

        if button.is_pressed: # 버튼이 눌렸을 경우
            # ---- 이미지 생성 ----
            now = time.time() # 현재 시간 저장 -> timestamp 형식
            now_date = str(datetime.datetime.now()) # 현재 시간 저장 -> 일반 년월일 형식
            times = str(int(now)) + ".jpg" # timestamp의 소숫점 제거 후 .jpg를 붙여 파일명 생성
            cv2.imwrite(times, frame) # 지정한 이미지 명으로 사진 저장 -> run.py가 있는 폴더에 <timestamp>.jpg 파일 생성
            # ---- 생성한 파일 S3 클라우드에 전송 ----
            s3.upload_file( # s3에 생성한 jpg파일 업로드
            times, bucket, folder + times, ExtraArgs={'ACL': 'public-read'} # s3에서 자체적으로 제공하는 파일 접근 URL을 사용하기 위해 ACL(접근 허가 목록)에서 주소를 가진 모든 사용자의 접근을 허가
            )
            table.put_item(
            Item={
                    'time': now_date,
                    'file': 'https://' + bucket + '.s3.ap-northeast-2.amazonaws.com/' +folder + times,
                } # file 형식은 AWS에서 자동으로 생성하는 파일 접근 URL의 생성 규칙 방식임 *사용중인 개인 서버의 리전에 맞도록 리전부분 변경*
            )
            # ---- 안드로이드 어플에 푸시 알림 전송 ----
            publish_multi_message(topic, "Hello from Learn AWS", "This is the default message", fcmmessage)

        
    cap.release() # 카메라 종료
    cv2.destroyAllWindows() # opencv 종료
    


