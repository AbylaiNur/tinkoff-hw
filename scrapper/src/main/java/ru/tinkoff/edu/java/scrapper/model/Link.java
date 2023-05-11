package ru.tinkoff.edu.java.scrapper.model;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.net.URI;
import java.time.OffsetDateTime;

@Data
@Accessors(chain = true)
@Entity
@Table(name="link")
public class Link {
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE)
    private Long id;
    private URI url;
    private OffsetDateTime lastChecked;
    private OffsetDateTime lastUpdated;
}
