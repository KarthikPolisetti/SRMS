# 1. The Base Environment (The "Saltwater")
FROM eclipse-temurin:17-jdk-alpine

# 2. Create a working directory inside the container
WORKDIR /app

# 3. Copy your compiled Spring Boot application into the container
# (Make sure you ran 'mvn clean package' before building this!)
COPY target/*.jar app.jar

# 4. Tell the container to open port 8080 to the outside world
EXPOSE 8080

# 5. The command that runs when the container wakes up
ENTRYPOINT ["java", "-jar", "app.jar"]