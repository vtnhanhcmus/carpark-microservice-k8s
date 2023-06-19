FROM openjdk:17-jdk-slim as build
COPY target/bookings-0.0.1-SNAPSHOT.jar bookings-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/bookings-0.0.1-SNAPSHOT.jar"]