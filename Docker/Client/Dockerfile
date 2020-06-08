FROM maven:3.6.3-amazoncorretto-8 AS CLIENT 

WORKDIR /tmp 

ENTRYPOINT [ "/run.sh" ]

# Use id command on the host os to find the username and the groupname of the current user

ARG USER_NAME=jaxon 
ARG USER_ID=1000
ARG GROUP_ID=1000

ENV DISPLAY=:99

RUN yum update -y && \
    yum install git sudo xorg-x11-server-Xvfb java-1.8.0-openjdk.x86_64 -y && \
    git clone https://github.com/MahaAmin/TicTacToe.git && \
    yum clean all && \
    rm -rf /var/cache/yum && \
    cd /tmp/TicTacToe/Client && \
    mvn clean compile && \
    mvn clean install 

RUN /usr/sbin/groupadd -g ${GROUP_ID} ${USER_NAME} && \
    /usr/sbin/useradd -d /home/${USER_NAME} -s /bin/bash -m ${USER_NAME} -u ${USER_ID} -g ${GROUP_ID}

COPY run.sh /run.sh

RUN chmod a+x /run.sh 

USER ${USER_NAME}

ENV HOME /home/${USER_NAME}

WORKDIR /tmp/TicTacToe/Client/target 

