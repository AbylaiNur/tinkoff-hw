FROM openjdk:17

ARG JAR_FILE=scrapper/target/scrapper-1.0-SNAPSHOT.jar

WORKDIR /app/scrapper

COPY ${JAR_FILE} scrapper.jar

ENTRYPOINT ["java", "-jar", "scrapper.jar"]
