# Specifies the base image we're extending
FROM eclipse-temurin:19-jdk

# Set the maintainer of the image
MAINTAINER "Athirson Silva"

# Set the working directory
WORKDIR /app

# Copy the jar file from the target folder to the container
COPY /target/*.jar /app/app.jar

# Expose the port
EXPOSE 8080

# Run the jar file
ENTRYPOINT ["java", "-jar", "app.jar"]