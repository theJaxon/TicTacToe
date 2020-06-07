# Tic Tac Toe
[![forthebadge](https://forthebadge.com/images/badges/made-with-java.svg)](https://forthebadge.com)
[![ForTheBadge built-with-love](http://ForTheBadge.com/images/badges/built-with-love.svg)](https://GitHub.com/Naereen/)
[![forthebadge](https://forthebadge.com/images/badges/contains-cat-gifs.svg)](https://forthebadge.com)
[![forthebadge](https://forthebadge.com/images/badges/uses-css.svg)](https://forthebadge.com)

A network based Tic tac toe game, this implementation was done as the java project @ ITI intake 40 by students of the open source cloud platform development track.

![hg](https://github.com/MahaAmin/TicTacToe/blob/master/GamePlay%20Preview.gif)

# Contents

- [Getting Started](#getting-started)
- [Prerequisites](#prerequisites)
 - [Installing](#installing)
 - [Diagrams](#diagrams)
	 - [Class Diagram](#class-diagram)
	 - [Database Schema](#database-schema)
  - [Features ](#features)
	  - [Feature 1](#feature-1)

## Getting Started

There are two ways to run the project.

-First through the terminal and jar files.
open the terminal and cd to the Server jar file directory and run:
```
java -jar ./Server.jar
```
the server dashboard will pop up and the server should now be listening to requests on the localhost port 5005

Next open another terminal and cd to the Client jar file directory and run:
```
java -jar ./Client.jar
```
and the login screen should open bare in mind the jar files are made that the client and the server both run on the same machine 
if you want to run them on different machines one small change should be made and it's to change the connection ip in the Client project in a class called PlayerSoc.java from 127.0.0.1 to the ip of the machine which the server is runing on.

-Second through the projects
it's as easy as running the Server project first then the Client project.

### Prerequisites

java 8u111 or higher recommended

```
Give examples
```

### Installing

A step by step series of examples that tell you how to get a development env running

Say what the step will be

```
Give the example
```

And repeat

```
until finished
```

End with an example of getting some data out of the system or using it for a little demo

## Features

Client Side Features:</br>
-login</br>
-SignUp</br>
-play with pc with 3 difficulty levels</br>
-play with online friends</br>
-chat while playing</br>
-have an avatar and score level</br>
-see who has the highest score in the game</br>
-see who is online offline or busy playing with someone else</br>
</br>
Server side Features:</br>
-see a list of all users</br> 
-see players status and score</br>
-close and reopen the server</br>
</br>
## Deployment

Add additional notes about how to deploy this on a live system

## Built With

* [JFoenix](http://www.jfoenix.com/) -JavaFX Material Design Library
* [Ikonli](http://kordamp.org/ikonli/) -Icon packs for Java applications
* [AnimateFX](https://typhon0.github.io/AnimateFX/) -A library of ready-to-use animations for JavaFX
* [Maven](https://maven.apache.org/) - Dependency Management
* [MySQL](https://dev.mysql.com/downloads/connector/j/) - JDBC Type 4 driver for MySQL
* [JSON-Simple](https://code.google.com/archive/p/json-simple/) -  A simple Java toolkit for JSON

## Contributing

Please read [CONTRIBUTING.md](https://gist.github.com/PurpleBooth/b24679402957c63ec426) for details on our code of conduct, and the process for submitting pull requests to us.


## Authors

* **Billie Thompson** - *Initial work* - [PurpleBooth](https://github.com/PurpleBooth)

See also the list of [contributors](https://github.com/your/project/contributors) who participated in this project.

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details

## Acknowledgments

* Hat tip to anyone whose code was used
* Inspiration
* etc

---

### Dockerizing the app:
Client Dockerfile:
```Dockerfile
FROM maven:3.6.3-amazoncorretto-8 AS CLIENT 

WORKDIR /tmp 

ENTRYPOINT [ "java" ]

CMD ["-jar", "TicTacToeFX-1.0-SNAPSHOT.jar"]

# Use id command on the host os to find the username and the groupname of the current user

ARG USER_NAME=jaxon 
ARG USER_ID=1000
ARG GROUP_ID=1000

ENV DISPLAY :1


RUN yum update -y && \
    yum install git sudo libX11 -y && \
    git clone https://github.com/MahaAmin/TicTacToe.git && \
    yum clean all && \
    rm -rf /var/cache/yum && \
    cd /tmp/TicTacToe/Client && \
    mvn clean compile && \
    mvn clean install && \
    cd /tmp/TicTacToe/Client/target 


RUN /usr/sbin/groupadd -g ${GROUP_ID} ${USER_NAME} && \
    /usr/sbin/useradd -d /home/${USER_NAME} -s /bin/bash -m ${USER_NAME} -u ${USER_ID} -g ${GROUP_ID}

USER ${USER_NAME}

ENV HOME /home/${USER_NAME}

WORKDIR /tmp/TicTacToe/Client/target 

```

```
docker build -t client .
```

```
docker run -v /tmp/.X11-unix/:/tmp/.X11-unix -h $HOSTNAME -v $HOME/.Xauthority:/home/$USER/.Xauthority -e DISPLAY=$DISPLAY client
```

At this point this file results in the following error and i couldn't get a solution for it for now so i still can't launch the GUI, the following error is returned
```
Exception in thread "main" java.lang.reflect.InvocationTargetException
	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
	at java.lang.reflect.Method.invoke(Method.java:498)
	at sun.launcher.LauncherHelper$FXHelper.main(LauncherHelper.java:767)
Caused by: java.lang.UnsupportedOperationException: Unable to open DISPLAY
	at com.sun.glass.ui.gtk.GtkApplication.lambda$new$5(GtkApplication.java:142)
	at java.security.AccessController.doPrivileged(Native Method)
	at com.sun.glass.ui.gtk.GtkApplication.<init>(GtkApplication.java:140)
	at com.sun.glass.ui.gtk.GtkPlatformFactory.createApplication(GtkPlatformFactory.java:41)
	at com.sun.glass.ui.Application.run(Application.java:146)
	at com.sun.javafx.tk.quantum.QuantumToolkit.startup(QuantumToolkit.java:257)
	at com.sun.javafx.application.PlatformImpl.startup(PlatformImpl.java:211)
	at com.sun.javafx.application.LauncherImpl.startToolkit(LauncherImpl.java:675)
	at com.sun.javafx.application.LauncherImpl.launchApplicationWithArgs(LauncherImpl.java:337)
	at com.sun.javafx.application.LauncherImpl.launchApplication(LauncherImpl.java:328)
	... 5 more
```