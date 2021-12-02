import cv2
import time

if __name__ == '__main__':
    cap = cv2.VideoCapture(0)

    if not cap.isOpened():
        print('[!] video open failed!')
        
    while True:
        ret, frame = cap.read()
        if not ret:
            break

        cv2.imshow('frame',frame)


        if cv2.waitKey(30) == 27:
            break

    now = time.time()
    times = str(int(now)) + ".jpg"
    cv2.imwrite(times, frame)

    cap.release()
    cv2.destroyAllWindows()
