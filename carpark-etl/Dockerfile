FROM openjdk:17-jdk-slim as build
COPY target/carpark-etl-0.0.1-SNAPSHOT.jar carpark-etl-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/carpark-etl-0.0.1-SNAPSHOT.jar"]