package ru.tinkoff.edu.java.scrapper.scheduler;

import org.springframework.scheduling.annotation.Scheduled;

public class LinkUpdaterScheduler {
    @Scheduled(fixedDelay = 5000)
    public void update() {
        System.out.println("update");
    }
}
