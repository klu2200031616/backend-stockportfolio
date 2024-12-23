# Use Maven to build the application
FROM  maven:3.9.8-amazoncorretto-17-al2023 AS  build
WORKDIR /app
copy pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Use OpenJDK to run the application
FROM openjdk:24-stin-bullseye
WORKDIR /app
COPY --from=buitd /app/target/splitify-backend-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 2024

ENTRYPOINT ["java ", "-jar", "app.jar"]