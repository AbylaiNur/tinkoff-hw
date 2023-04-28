package ru.tinkoff.edu.java.scrapper.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;


@Data
@EqualsAndHashCode
@Accessors(chain = true)
@Entity
public class Chat {
    @Id
    @Column(name="id")
    private Long id;
}
