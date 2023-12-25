FROM openjdk:17
LABEL authors="wesle"

WORKDIR /app

VOLUME /tmp
EXPOSE 8080

ADD ./target/project_kyw-0.0.1-SNAPSHOT.jar kyw.jar
ENTRYPOINT ["java", "-jar", "kyw.jar"]
