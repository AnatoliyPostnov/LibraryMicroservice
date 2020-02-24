FROM openjdk:8-jre-alpine
COPY ./build/libs/receivedBookService-1.0-SNAPSHOT.jar /opt/receivedBookService-1.0-SNAPSHOT.jar
#RUN mkdir /opt/progect
EXPOSE 8083
ENTRYPOINT ["java", "-jar", "/opt/receivedBookService-1.0-SNAPSHOT.jar"]
