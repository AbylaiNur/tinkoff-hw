package ru.tinkoff.edu.java.scrapper.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

@Configuration
public class SchedulerConfiguration {

    @Bean
    public Duration updateInterval(ApplicationConfig applicationConfig) {
        return applicationConfig.scheduler().interval();
    }
}
