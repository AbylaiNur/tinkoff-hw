package ru.tinkoff.edu.java.scrapper.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.OffsetDateTime;

@Data
@EqualsAndHashCode
@Accessors(chain = true)
@Entity
public class Link {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(name="url")
    private String url;

    @Column(name="last_checked")
    private OffsetDateTime lastChecked;

    @Column(name="last_updated")
    private OffsetDateTime lastUpdated;
}
