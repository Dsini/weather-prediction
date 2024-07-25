
FROM eclipse-temurin:17-jre-alpine as build


WORKDIR /app

COPY target/*.jar /app/application.jar

EXPOSE 8080

CMD ["java", "-jar", "application.jar"]