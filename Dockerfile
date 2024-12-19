FROM openjdk:17-jdk-alpine


MAINTAINER group
COPY target/ticket-system.0.0.1.jar ticket-system.0.0.1.jar
ENTRYPOINT ["java", "-jar", "ticket-system.0.0.1.jar"]