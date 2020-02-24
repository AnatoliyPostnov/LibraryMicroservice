FROM openjdk:8-jre-alpine
EXPOSE 8082
ADD ./build/libs/ClientService-1.0-SNAPSHOT.jar /
ENTRYPOINT ["java", "-jar", "ClientService-1.0-SNAPSHOT.jar"]
