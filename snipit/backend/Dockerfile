# Using a lightweight java 17 runtime (Alpine Linux)
FROM amazoncorretto:17-alpine

#Adding metadata
LABEL maintaner="GenSeven"

#Creating a folder inside the container
WORKDIR /app

# 4. Copy the Fat JAR from your laptop into the container
# Note:Run docker build from the 'snipit' folder
COPY backend/target/scala-2.13/snipit-backend-assembly-0.1.0.jar app.jar

EXPOSE 8000

# 7. The Command to run when the container starts
CMD ["java", "-jar", "app.jar"]

