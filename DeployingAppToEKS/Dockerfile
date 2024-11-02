# Use OpenJDK 17 as the base image
FROM amazoncorretto:17

# Set the working directory
WORKDIR /app

# Copy the jar file from Maven's target folder to the image
COPY target/DeployingAppToEKS-1.0-SNAPSHOT.jar app.jar

# Define the entrypoint
ENTRYPOINT ["java", "-jar", "app.jar"]
