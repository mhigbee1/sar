# sar
Share-a-Ride API for IIT-CS445 Final Project using Object Oriented Design

This project is meant to be installed and run on a fresh implementation of Ubuntu 18.04 LTS.

Installation Instructions:

Update the packaging tool:
$ sudo apt update

Install Java:
$ sudo apt install openjdk-8-jdk

Install Gradle:
$ sudo apt install curl

$ curl -s "https://get.sdkman.io" | bash

Enter the source command given by sdkman at the end of the download message

$ sdk install gradle 5.1.1


Install Unzip:
$ sudo apt install unzip


Install Apache Tomcat 9:
$ cd ~/Downloads

$ wget https://downloads.apache.org/tomcat/tomcat-9/v9.0.43/bin/apache-tomcat-9.0.43.zip

$ unzip apache-tomcat-9.0.43.zip

$ sudo mv apache-tomcat-9.0.43 /opt/apache-tomcat


To build the Ride Share project:
Navigate to a directory where you want to hold the project.

Install git:
$ sudo apt install git

Clone the github repository:
$ git clone https://github.com/mhigbee1/sar.git

Create the gradle wrapper:
$ cd sar/sar

$ gradle wrapper

Build the project:
$ ./gradlew build


To deploy the Ride Share project:
Find the WAR file generated in build:
$ cd build/libs

Copy the WAR file into Tomcat webapps folder:
$ cp sar.war /opt/apache-tomcat/webapps

Compile the catalina.sh file:
$ cd /opt/apache-tomcat/bin

$ chmod +x ./catalina.sh

Run the catalina executable:
$ ./catalina.sh run


Exiting the Server:
To leave the server:
Ctrl+C


Testing Reports:

To run the testing coverage report:
$ ./gradlew jacocoTestReport

The test can be viewed using the browser:
~/../[DirectorName]/build/reports/test/html
*substitute the name of the directory where you saved the cloned repository for [DirectoryName]


