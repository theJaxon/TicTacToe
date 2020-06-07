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

