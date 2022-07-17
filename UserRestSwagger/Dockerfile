#### Stage 1: Build the application
FROM openjdk:8-jdk
EXPOSE 8090

# Set the current working directory inside the image
WORKDIR /app

# Copy maven executable to the image
COPY mvnw .
COPY .mvn .mvn

# Copy the pom.xml file
COPY pom.xml ./pom.xml


RUN chmod 755 /app/mvnw
RUN ./mvnw dependency:go-offline -B -Dmaven.artifact.threads=35

RUN ./mvnw package #-DskipTests
#RUN ls -al
ENTRYPOINT ["java","-jar","target/swagger-0.0.1-SNAPSHOT.jar"]