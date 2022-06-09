FROM openjdk:11-jre

LABEL maintainer="nha.huynh@dirox.net"

ADD target/executable-rest-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

CMD ["java", "-jar", "app.jar"]