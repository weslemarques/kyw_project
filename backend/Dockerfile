FROM openjdk:17-alpine

ENV APP_NAME kyw_project
LABEL authors="wesle"
VOLUME /tmp
ADD ../target/${APP_NAME}.jar /app/${APP_NAME}.jar
WORKDIR /app
CMD java -jar ${APP_NAME}.jar
EXPOSE 8080

