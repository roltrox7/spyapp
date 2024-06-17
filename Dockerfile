# Use a base image with OpenJDK 11
FROM adoptopenjdk/openjdk11

# Expose port 8080
EXPOSE 8080

# Set environment variable for application home directory
ENV APP_HOME /usr/src/app

# Copy the JAR file from the target directory into the container's APP_HOME directory
COPY target/*.jar $APP_HOME/app.jar

# Copy HTML files
COPY src/main/resources/templates $APP_HOME/templates

# Set the working directory inside the container to APP_HOME
WORKDIR $APP_HOME

# Command to run your Spring Boot application
CMD ["java", "-jar", "app.jar"]
