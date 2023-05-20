# Telegram Bot Update Notifier - Tinkoff Academy Course Project

## Description

Developed during the Tinkoff Academy course, this project includes two web services that help track changes in GitHub repositories and StackOverflow questions through their respective links.

Within the Telegram bot, we can track, remove, and list links. Additionally, the Telegram bot notifies users in real time about changes made to the repositories and questions.

The project consists of three Maven modules:

- bot - the Telegram bot
- link-parser - responsible for parsing the content of URLs
- scrapper - scrapes, stores tracked links and monitors changes

## Technologies Used

The telegram bot was built using Spring Boot and Java Telegram Bot API.

Other: Maven, Docker, Hibernate, Liquibase, PostgreSql, Testcontainers, RabbitMQ, Prometheus and Grafana

## Table of Contents

1. [Installation](#installation)
2. [Usage](#usage)
3. [Credits](#credits)

## Installation

This is a multi-module Java Spring application.

To set up the environment:

1. Clone the repository: `git clone https://github.com/AbylaiNur/tinkoff-hw.git`
2. Navigate into the project directory: `cd tinkoff-hw`

You will find three modules:

- `bot`
- `link-parser`
- `scrapper`

Bot and scrapper modules can be built separately.

bot module:
```
cd bot
docker build -t bot:latest .
docker run -p 8081:8081 -e TELEGRAM_BOT_TOKEN=<<tg_bot_token_value>> bot:latest
```

scrapper module:
```
cd scrapper
docker compose up -d
docker build -t scrapper:latest .
docker run -p 8080:8080 scrapper:latest
```


Now, let's start the Prometheus and Grafana services:

Navigate to the root directory.
Start the Prometheus and Grafana services: 
```
docker compose up -d
```

## Usage
After completing the installation steps, you should have all modules and services up and running.

To access the application, navigate to localhost followed by the appropriate port in your browser. Note that the port will depend on how you have configured your services.

To monitor your services with Prometheus and Grafana, you can navigate to:

Prometheus: http://localhost:9090
Grafana: http://localhost:3000

## Credits

This project is created by:

- [Abylai Nurske](https://github.com/AbyaliNur)

The course in which the project was created:
- [Course](https://fintech.tinkoff.ru/academy/java)
