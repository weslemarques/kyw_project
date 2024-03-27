FROM openjdk:17-alpine
ENV APP_NAME kyw_project
LABEL authors="wesle"
VOLUME /tmp
COPY ./target/${APP_NAME}.jar /app/${APP_NAME}.jar
WORKDIR /app
ENTRYPOINT [ "java", "-jar","${APP_NAME}.jar"]
EXPOSE 8080

