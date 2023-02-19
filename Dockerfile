FROM openjdk:11
COPY target/web-api-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar", "-Dspring.profiles.active=dev", "/app.jar"]