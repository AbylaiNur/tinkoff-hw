package ru.tinkoff.edu.java.scrapper.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;


@Data
@EqualsAndHashCode
@Accessors(chain = true)
@Entity
public class Chat {
    @Id
    @Column(name = "id")
    private Long id;
}
