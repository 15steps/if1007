FROM openjdk:8-jdk-alpine
VOLUME /tmp
ARG JAR_FILE=build/libs
COPY ${JAR_FILE}/orders-api.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]