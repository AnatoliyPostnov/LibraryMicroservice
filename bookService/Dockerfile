FROM openjdk:8-jre-alpine
COPY ./build/libs/BookService-1.0-SNAPSHOT.jar /opt/BookService-1.0-SNAPSHOT.jar
RUN mkdir /opt/progect
#EXPOSE 8081
ENTRYPOINT ["java", "-jar", "/opt/BookService-1.0-SNAPSHOT.jar"]
