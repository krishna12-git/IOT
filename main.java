6 cide )
void setup() {
 pinMode(2, INPUT);
 pinMode(3, INPUT);
 pinMode(10, OUTPUT);
 pinMode(11, OUTPUT);
 pinMode(12, OUTPUT);
 pinMode(13, OUTPUT);
 }
 void loop() {
 int v = digitalRead(2);
 int s = digitalRead(3);
 if(v == 1 and s == 1){
 digitalWrite(13, 1);
 digitalWrite(12, 0);
 digitalWrite(11, 1);
 digitalWrite(10, 0);
 }
 if(v == 1 and s == 0){
 digitalWrite(13, 0);
 digitalWrite(12, 1);
 digitalWrite(11, 1);
 digitalWrite(10, 0);
 }
 if(v == 0 and s == 1){
 digitalWrite(13, 1);
 digitalWrite(12, 0);
 digitalWrite(11, 0);
 digitalWrite(10, 1);
 }

 if(v == 0 and s == 0){
 digitalWrite(13, 0);
 digitalWrite(12, 1);
 digitalWrite(11, 0);
 digitalWrite(10, 1);
 }
}

7 )

#include "LiquidCrystal.h"  //lcd libary                                      
LiquidCrystal lcd(2, 3, 4, 5, 6, 7);   //LCD object Parameters: (rs, enable, d4, d5, d6, d7)
const int trigPin = 12; //trig pin connection
const int echoPin = 11;  //echopin connection
long duration;
int distanceCm;
float liquid;
                                                                                                           
void setup() {      // setup perameter
lcd.begin(16,2);                                                  
pinMode(trigPin, OUTPUT);
pinMode(echoPin, INPUT);
lcd.setCursor(0,0);
lcd.print("  Distance    ");
lcd.setCursor(0,1);
lcd.print("  Measurement  ");
delay(2000);
lcd.clear();
}

void loop() {   // loop of flow program
digitalWrite(trigPin, LOW);
delayMicroseconds(2);
digitalWrite(trigPin, HIGH);
delayMicroseconds(10);
digitalWrite(trigPin, LOW);
duration = pulseIn(echoPin, HIGH);
distanceCm= duration*0.034/2;                                                                                
lcd.setCursor(0,0);                                                
lcd.print("Distance Measur.");
delay(10);
lcd.setCursor(0,1);
lcd.print("Distance:");
lcd.print(distanceCm);
lcd.print(" Cm ");
delay(10);
}

8)8.
 import time
import RPi.GPIO as GPIO
GPIO.setmode(GPIO.BOARD)
GPIO.setwarnings(False)


Define GPIO to LCD mapping
Led_pin = 7
PIR_Sensor = 13

GPIO.setup(Led_pin, GPIO.OUT)  # E
GPIO.setup(PIR_Sensor, GPIO.IN)
Define some device constants

while 1:
  if(GPIO.input(PIR_Sensor)):
   GPIO.output(Led_pin, True)
   time.sleep(1)  
  else:
   GPIO.output(Led_pin, False)
   time.sleep(1)