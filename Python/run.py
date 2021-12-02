from gpiozero import Button
import cv2
import time
import datetime
import boto3

if __name__ == '__main__':

    # Let's use Amazon Web Service
    s3 = boto3.client('s3') # S3
    dynamodb = boto3.resource('dynamodb') # Dynamodb
    table = dynamodb.Table('doorbell') # Dynamodb -> table
    cap = cv2.VideoCapture(0) # Video capture
    button = Button(2) # button

    bucket = 'lambda-function-bucket-ap-northeast-2-1635049288998'
    folder = 'picture/'

    if not cap.isOpened():
        print('[!] video open failed!')

    while True:
        ret, frame = cap.read()
        if not ret:
            break

        cv2.imshow('frame',frame)

        if cv2.waitKey(30) == 27:
            break

        if button.is_pressed:
            now = time.time()
            now_date = str(datetime.datetime.now())
            times = str(int(now)) + ".jpg"
            cv2.imwrite(times, frame)
            # Upload a new file
            s3.upload_file(
            times, bucket, folder + times, ExtraArgs={'ACL': 'public-read'}
            )
            table.put_item(
            Item={
                    'time': now_date,
                    'file': 'https://' + bucket + '.s3.ap-northeast-2.amazonaws.com/' +folder + times,
                }
)
        
    cap.release()
    cv2.destroyAllWindows()
