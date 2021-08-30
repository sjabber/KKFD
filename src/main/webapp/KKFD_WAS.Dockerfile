FROM openjdk:8-jdk

MAINTAINER Tae ho Kim <sjabber91@gmail.com>

RUN mkdir -p /api

WORKDIR /api

COPY ./KKFD-0.0.1-SNAPSHOT.jar .

ENTRYPOINT ["java","-jar","KKFD-0.0.1-SNAPSHOT.jar"]