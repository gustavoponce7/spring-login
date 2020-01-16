FROM openjdk:11

EXPOSE 8080

ARG JAR_FILE=target/login-tutorial.jar
ADD ${JAR_FILE} login-tutorial.jar

ENTRYPOINT ["java", "-jar", "/login-tutorial.jar"]