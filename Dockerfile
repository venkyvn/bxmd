# First stage: Build the application
FROM gradle:7.4.1-jdk17 AS build

# Set the working directory
WORKDIR /app

# Copy the Gradle wrapper and build files
COPY gradle gradle
COPY gradlew .
COPY build.gradle.kts .
COPY settings.gradle.kts .

# Copy the application source code
COPY src src

# Build the application
RUN ./gradlew build

# Second stage: Create the Docker image
FROM openjdk:17-jdk-slim

# Set the working directory
WORKDIR /app

# Copy the JAR file from the build stage
COPY --from=build /app/build/libs/*.jar app.jar

# Expose port 8080
EXPOSE 8080

## Set environment variables
#ENV SPRING_DATASOURCE_URL=jdbc:postgresql://db:5432/yourdatabase
#ENV SPRING_DATASOURCE_USERNAME=yourusername
#ENV SPRING_DATASOURCE_PASSWORD=yourpassword
#ENV SPRING_JPA_HIBERNATE_DDL_AUTO=none
#ENV SPRING_FLYWAY_ENABLED=true
#ENV SPRING_FLYWAY_LOCATIONS=classpath:db/migration

# Run the JAR file
ENTRYPOINT ["java", "-jar", "app.jar"]
