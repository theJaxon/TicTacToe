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

---

### Dockerizing the app:
#### Client Dockerfile:

```Dockerfile
FROM maven:3.6.3-amazoncorretto-8 AS CLIENT 

WORKDIR /tmp 

ENTRYPOINT [ "/Client.sh" ]

# Use id command on the host os to find the username and the groupname of the current user

ARG USER_NAME=jaxon 
ARG USER_ID=1000
ARG GROUP_ID=1000

ENV DISPLAY=:99

RUN yum update -y && \
    yum install git sudo xorg-x11-server-Xvfb java-1.8.0-openjdk.x86_64 -y && \
    git clone https://github.com/theJaxon/TicTacToe.git && \
    yum clean all && \
    rm -rf /var/cache/yum && \
    cd /tmp/TicTacToe/Client && \
    mvn clean compile && \
    mvn clean install 

RUN /usr/sbin/groupadd -g ${GROUP_ID} ${USER_NAME} && \
    /usr/sbin/useradd -d /home/${USER_NAME} -s /bin/bash -m ${USER_NAME} -u ${USER_ID} -g ${GROUP_ID}

COPY Client.sh /Client.sh

RUN chmod a+x /Client.sh 

USER ${USER_NAME}

ENV HOME /home/${USER_NAME}

WORKDIR /tmp/TicTacToe/Client/target 

```

Client.sh script:
```bash
#!/bin/bash
Xvfb :99 -screen 0 640x480x8 -nolisten tcp &
java -Dprism.order=sw -jar TicTacToeFX-1.0-SNAPSHOT.jar
```

#### Server Dockerfile:
```Dockerfile
FROM maven:3.6.3-amazoncorretto-8 AS SERVER 

WORKDIR /tmp 

ENTRYPOINT [ "/Server.sh" ]

# Use id command on the host os to find the username and the groupname of the current user

ARG USER_NAME=jaxon 
ARG USER_ID=1000
ARG GROUP_ID=1000

ENV DISPLAY=:99


RUN yum update -y && \
    yum install git sudo xorg-x11-server-Xvfb java-1.8.0-openjdk.x86_64 -y && \
    git clone https://github.com/theJaxon/TicTacToe.git && \
    yum clean all && \
    rm -rf /var/cache/yum && \
    cd /tmp/TicTacToe/Server && \
    mvn clean compile && \
    mvn clean install 

RUN /usr/sbin/groupadd -g ${GROUP_ID} ${USER_NAME} && \
    /usr/sbin/useradd -d /home/${USER_NAME} -s /bin/bash -m ${USER_NAME} -u ${USER_ID} -g ${GROUP_ID}


COPY Server.sh /Server.sh

RUN chmod a+x /Server.sh 

USER ${USER_NAME}

ENV HOME /home/${USER_NAME}

WORKDIR /tmp/TicTacToe/Server/target 
```

Server.sh
```bash
#!/bin/bash
Xvfb :99 -screen 0 640x480x8 -nolisten tcp &
java -Dprism.order=sw -jar Server-1.0-SNAPSHOT.jar
```

#### MySQL Dockerfile:
```Dockerfile
FROM mysql:5

COPY ./tictactoe.sql /docker-entrypoint-initdb.d
```

#### docker-compose:
```yaml
version: "3.8"
services:
  mysql:
    build: ./MySQL
    expose:
      - "3306"
    ports: 
      - "3306:3306"
    environment:
      - MYSQL_ROOT_PASSWORD=root
      - MYSQL_DATABASE=tictactoe
      - MYSQL_USER=maha
      - MYSQL_PASSWORD=maha
    


  client:
    build: ./Client
    hostname: $HOSTNAME
    volumes:
      - type: bind
        source: /tmp/.X11-unix
        target: /tmp/.X11-unix 

      - type: bind
        source: $HOME/.Xauthority
        target: /home/$USER/.Xauthority
    environment:
      - DISPLAY=$DISPLAY

    depends_on:
      - server 

  server:
    build: ./Server
    hostname: $HOSTNAME
    expose: 
      - "5005"
    ports: 
      - "5005:5005"
    volumes:
      - type: bind
        source: /tmp/.X11-unix
        target: /tmp/.X11-unix 

      - type: bind
        source: $HOME/.Xauthority
        target: /home/$USER/.Xauthority
    environment:
      - DISPLAY=$DISPLAY
    restart: always

    depends_on:
    - mysql

```

#### How to use it:
```bash
docker-compose up -d
# Wait till everything starts then restart the client using 
docker-compose restart client
```
Sign up for an account and you're set to play the game.