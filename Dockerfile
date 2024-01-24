FROM openjdk:21-jdk-slim

COPY target/drone-delivery.jar /deploy/

EXPOSE 8080
USER 1001

ENTRYPOINT ["java", "-jar", "/deploy/drone-delivery.jar"]