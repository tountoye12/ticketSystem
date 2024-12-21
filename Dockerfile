# Use OpenJDK 17 Alpine as the base image
FROM openjdk:17-jdk

# Set the maintainer information
MAINTAINER group


WORKDIR /app


COPY ./target/ticket-system.0.0.1.jar ticket-system.0.0.1.jar

# Set the entry point for the application
ENTRYPOINT ["java", "-jar", "ticket-system.0.0.1.jar"]
