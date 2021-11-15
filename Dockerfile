FROM openjdk:17-alpine
LABEL maintrainer = "Alexey"
COPY out/artifacts/test_jwt_messenger_jar/*.jar /app.jar
EXPOSE 8087
ENTRYPOINT ["java","-jar","/app.jar"]