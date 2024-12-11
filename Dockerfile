FROM openjdk:17-jdk-alpine


MAINTAINER group
COPY target/api.0.0.1.jar api.0.0.1.jar
ENTRYPOINT ["java", "-jar", "api.0.0.jar"]