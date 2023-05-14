FROM openjdk:17

ARG JAR_FILE=bot/target/bot-1.0-SNAPSHOT.jar

WORKDIR /app/bot

COPY ${JAR_FILE} bot.jar

ENTRYPOINT ["java", "-jar", "bot.jar"]