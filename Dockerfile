FROM openjdk:21-jdk
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} kkoalla.jar
ENTRYPOINT ["java","-jar","/kkoalla.jar"]