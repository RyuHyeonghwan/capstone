from flask import Flask, render_template, Response
import argparse
import cv2
from detect_flask import *

 
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
