FROM openjdk:11-jre-slim

ARG JAR_FILE=./build/libs/*.jar
ENV TZ=Asia/Seoul
ENV eureka.client.serviceUrl.defaultZone=http://discovery-prod:8761/eureka

RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

COPY ${JAR_FILE} app.jar

EXPOSE 8087
ENTRYPOINT ["java", "-jar", "/app.jar"]