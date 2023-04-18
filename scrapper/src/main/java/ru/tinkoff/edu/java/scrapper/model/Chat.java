package ru.tinkoff.edu.java.scrapper.model;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class Chat {
    private Long id;
    private String botState;
    private String botLastActiveCommand;
}
