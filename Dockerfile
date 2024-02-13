FROM eclipse-temurin:21-jre

COPY *.jar /app/user-service.jar

WORKDIR /app

CMD ["java", "-jar", "user-service.jar"]