FROM maven:3.9.8-eclipse-temurin-21 AS build
WORKDIR /app
COPY src /app/src
COPY pom.xml /app
RUN mvn clean package -DskipTests
RUN ls -la /app/target/

FROM openjdk:21
WORKDIR /app
COPY --from=build /app/target/gymesc-0.0.1-SNAPSHOT.jar /app/gymesc-0.0.1-SNAPSHOT.jar
EXPOSE 8080
CMD ["java", "-jar", "/app/gymesc-0.0.1-SNAPSHOT.jar"]
