# Stage 1: Build the application
FROM gradle:8.5.0-jdk17-focal AS builder

# Set the working directory in the container
WORKDIR /app

# Copy the Gradle build files
COPY build.gradle .
COPY settings.gradle .
COPY ./gradle ./gradle
COPY gradlew .

# Copy the source code
COPY src src
RUN ./gradlew clean
RUN ./gradlew test
# Build the application
RUN ./gradlew build

# Stage 2: Run the application
FROM openjdk:17.0.1-slim AS base


# Set the working directory in the container
WORKDIR /app

# Copy the JAR file from the builder stage to the current location in the container
COPY --from=builder /app/build/libs/assignment-bank-api-0.0.1-SNAPSHOT.jar app.jar

# Expose the port the application runs on
EXPOSE 8080

# Run the JAR file
CMD ["java", "-jar", "app.jar"]
