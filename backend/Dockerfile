FROM openjdk:17
LABEL authors="wesle"
VOLUME /tmp
EXPOSE 8080
COPY ./target/project_kyw-0.0.1-SNAPSHOT.jar /app/kyw.jar
WORKDIR /app
ENTRYPOINT ["java", "-jar", "kyw.jar"]

