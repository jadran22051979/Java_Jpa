FROM eclipse-temurin:21-jdk-alpine
LABEL authors="Jadran"
COPY target/Java_Jpa-0.0.1-SNAPSHOT.jar proba.jar
EXPOSE 8883
ENTRYPOINT ["java", "-jar","/proba.jar"]