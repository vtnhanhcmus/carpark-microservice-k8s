FROM openjdk:17-jdk-slim as build
COPY target/carparks-0.0.1-SNAPSHOT.jar carparks-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/carparks-0.0.1-SNAPSHOT.jar"]