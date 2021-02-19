/*Moves Servo motor for specified time*/

// Include Servo library 
#include <Servo.h> 

// set the pin 
int servoPin = 3; 

// Create a servo object 
Servo Servo1; 

void setup() { 
   // attach the pin number
   Servo1.attach(servoPin); 
}

void loop(){ 

   // servo 0 degrees
   moveServo(0); 
   
   // servo 90 degrees
   moveServo(90);
  
   // servo 180 degrees  
   moveServo(180) 
}

//move servo to specified location and wait the specified time
void moveServo(int degrees, int time){
  Servo1.write(degrees); 
  delay(time);
}
