FROM openjdk:14-alpine
VOLUME /tmp
COPY target/paperfox-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app.jar"]