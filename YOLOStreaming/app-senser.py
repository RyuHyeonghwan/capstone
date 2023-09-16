from flask import Flask, render_template, Response
import argparse
import cv2
from detect_flask import *
import serial
import pymysql

class VideoCamera(object):
    def __init__(self):
        self.video = cv2.VideoCapture(0)

    def __del__(self):
        self.video.release()
    def get_frame(self):
        success, image = self.video.read()
        return image

app = Flask(__name__)

@app.route('/')
def index():
    return render_template('index.html')

def detect(camera):
    detect_flask = run()
    while True:
        frame = camera.get_frame()
        ret, jpeg = cv2.imencode('.jpg', frame)
        frame = jpeg.tobytes()
        yield (b'--frame\r\n'
               b'Content-Type: image/jpeg\r\n\r\n' + frame + b'\r\n\r\n')



@app.route('/video_feed')
def video_feed():
            return Response(run(),
                        mimetype='multipart/x-mixed-replace; boundary=frame')

if __name__ == '__main__':
    app.run(host='0.0.0.0', debug=False, port=5000)


conn = None
cur = None

port = serial.Serial("/dev/ttyACM0", "9600")

conn = pymysql.connect(host='localhost', port=558323, user='capstone', password='capstone22_DB', db='senser',
                       charset='utf8')
curs = conn.cursor()

while True:
    try:
        data = port.readline().decode()
        data = data.split()
        print(data)
        co2ppm = float(data[0])
        temp = int(data[1])
        lpgppm = float(data[2])
        ch4ppm = float(data[3])
        coppm = float(data[4])

        # """INSERT INTO 테이블명 (열 이름) VALUES (데이터의 형태)""", (저장할 값) 형태.
        #curs.execute("""INSERT INTO Measure (CoValue, Temp, Co2Value, LPGValue) VALUES (%s, %s, %s, %s)""",(coppm, temp, co2ppm, lpgppm))
        #conn.commit()

        if (coppm > float(20)) and (lpgppm > float(100)):
            stage1 = 'Y'
            stage2 = 'Y'
            curs.execute("""INSERT INTO firedetect (CoValue, LPGValue, Stage2, Stage1) VALUES (%s, %s, %s, %s)""",(coppm, lpgppm, stage2, stage1))
            curs.execute("""INSERT INTO Measure (CoValue, Temp, Co2Value, LPGValue) VALUES (%s, %s, %s, %s)""",(coppm, temp, co2ppm, lpgppm))
            conn.commit()
                
        elif coppm > float(20) or lpgppm > float(100):
            stage1 = 'Y'
            curs.execute("""INSERT INTO firedetect (CoValue, LPGValue, Stage1) VALUES (%s, %s, %s)""",(coppm, lpgppm, stage1))
            curs.execute("""INSERT INTO Measure (CoValue, Temp, Co2Value, LPGValue) VALUES (%s, %s, %s, %s)""",(coppm, temp, co2ppm, lpgppm))
            conn.commit()
        else:
                stage1 = 'N'
                stage2 = 'N'
                curs.execute("""INSERT INTO firedetect (Stage1, Stage2) VALUES (%s, %s)""",(stage1, stage2))
                curs.execute("""INSERT INTO Measure (CoValue, Temp, Co2Value, LPGValue) VALUES (%s, %s, %s, %s)""",(coppm, temp, co2ppm, lpgppm))
                conn.commit()




    except KeyboardInterrupt:
        break

port.close()
conn.close()
