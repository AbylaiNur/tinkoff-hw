package ru.tinkoff.edu.java.scrapper.component.update.processor;

import ru.tinkoff.edu.java.scrapper.model.Link;

public interface LinkUpdateProcessor {
    void process(Link link);
    boolean canProcess(Link link);
}
