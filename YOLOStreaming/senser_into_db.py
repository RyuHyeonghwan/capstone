from app import *
from detect_flask import *
import serial
import pymysql
 

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



