# Build stage
FROM maven:3.8-openjdk-17 AS build

WORKDIR /app

COPY ./pom.xml .

RUN mvn dependency:go-offline
#copy src /app/src

COPY ./src ./src
RUN mvn package -DskipTests

# Use a minimal base image for the runtime (OpenJDK 17)
FROM openjdk:17-jdk-alpine AS runner

# Set working directory for runtime stage
WORKDIR /app

# Expose port 8080
EXPOSE 8080

# Copy the built JAR file from the build stage
COPY --from=build /app/target/*.jar /app/app.jar

# Set the entry point to run the JAR file
ENTRYPOINT ["java", "-jar", "/app/app.jar"]