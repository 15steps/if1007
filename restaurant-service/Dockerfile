FROM openjdk:8-jdk-alpine
VOLUME /tmp
ARG JAR_FILE=build/libs
COPY ${JAR_FILE}/restaurant-service.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]