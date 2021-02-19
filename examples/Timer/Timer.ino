/* A timer class to allow the user to create loops and maintain program control
   Also demonstrates the Author, Organization, and Supported Boards features
   Author: Jacob Smith
   Organization: Brandeis Robotics Club
   Boards Supported: ARDUINO_AVR_UNO ESP8266 */
   
//the beginning time of the interval
long initTime;

void setup(){
  initTime=millis();
  Serial.begin(9600);
}

void loop(){
  Serial.println("Getting time at 2 and 4 seconds");
  Serial.println(getTime());
  delay(2000);
  Serial.println(getTime());
  delay(2000);
  Serial.println("Resetting Time");
  Serial.println(resetTime());
  Serial.println(getTime());
}

//resets the Initial Time
long resetTime(){
  initTime=millis();
  return getTime();
}

//returns the current time
long getTime(){
  return millis()-initTime;
}
