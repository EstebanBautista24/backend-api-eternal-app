FROM amazoncorretto:22-alpine-jdk
COPY target/api-0.0.1-SNAPSHOT.jar app.jar
LABEL authors="Esteban"

