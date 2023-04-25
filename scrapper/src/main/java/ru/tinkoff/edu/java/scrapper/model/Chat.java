package ru.tinkoff.edu.java.scrapper.model;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Data
@Accessors(chain = true)
@Entity
@Table(name="chat")
public class Chat {
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE)
    private Long id;
}
