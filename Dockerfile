FROM ubuntu:latest

EXPOSE 8080

RUN apt-get update -y; apt-get install -y openjdk-11-jdk

COPY . /root/

WORKDIR /root/

RUN ./gradlew build -x test

CMD java -jar build/libs/restapi-0.0.1-SNAPSHOT.jar