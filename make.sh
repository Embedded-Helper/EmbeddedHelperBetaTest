#!/usr/bin/env bash
#Freelancer 6/15/2020 Embedded Helper
#Bash file to compile a java source file into an Arduino tool .jar file
#source if statement https://stackoverflow.com/questions/4846007/check-if-directory-exists-and-delete-in-one-command-unix
#Define Installer Directory
INSTALLDIR="C:/Users/jsmit/Documents/ArduinoSketches"
echo "INSTALLDIR: $INSTALLDIR"
# Define ARDUINO_HOME
ARDUINO_HOME='C:/Program Files (x86)/Arduino'
echo "ARDUINO_HOME: $ARDUINO_HOME"
#location of Jar file containing tool source code
TOOL_SOURCE_LOC='C:/Users/jsmit/Documents/GitHub/EmbeddedHelperSourceCode/src/cc/arduinoclassmaker.jar'
#echo "TOOLSOURCELOCATION: $TOOL_SOURCE_LOC"
#Define Name of Main Class and Jar name
PROJECT=EmbeddedHelper
echo "PROJECT: $PROJECT"
#Make bin directory
mkdir -p bin
#compile the .java file into a .class file 
#the target must be 1.8 or Arduino won't launch, use arduino_debug.exe to see error
#javac -target 1.8 -source 1.8 -cp "$ARDUINO_HOME/lib/arduino-core.jar;$ARDUINO_HOME/lib/pde.jar;$TOOL_SOURCE_LOC" -d bin  src/com/$PROJECT/$PROJECT.java  
javac -target 1.8 -source 1.8 -cp "$ARDUINO_HOME/lib/arduino-core.jar;$ARDUINO_HOME/lib/pde.jar;dependencies/arduinoclassmaker.jar" -d bin  src/com/$PROJECT/$PROJECT.java
#create the jar file
#this shouldn't be necessary but arduino crashes without it
cd bin
#make the correct tools structure
mkdir -p $INSTALLDIR/tools
rm -rf $INSTALLDIR/tools/$PROJECT
mkdir -p $INSTALLDIR/tools/$PROJECT/tool
#place the jar file in the tools structure
zip -r $INSTALLDIR/tools/$PROJECT/tool/$PROJECT.jar *
#copy the arduinoclassmaker jar file to the tools directory
cd ..
cp dependencies/arduinoclassmaker.jar $INSTALLDIR/tools/$PROJECT/tool/arduinoclassmaker.jar
