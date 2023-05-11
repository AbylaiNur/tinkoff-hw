package ru.tinkoff.edu.java.scrapper.component.update.processor;

import lombok.Getter;
import ru.tinkoff.edu.java.scrapper.model.Link;

@Getter
public abstract class LinkUpdateProcessor {
    private String host;
    public abstract void process(Link link);
}
