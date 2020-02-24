FROM openjdk:8-jre-alpine
ADD ./build/libs/emailSender-1.0-SNAPSHOT.jar /
EXPOSE 8084
ENTRYPOINT ["java", "-jar", "emailSender-1.0-SNAPSHOT.jar"]
