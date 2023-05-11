package ru.tinkoff.edu.java.scrapper.repository;

import ru.tinkoff.edu.java.scrapper.model.Chat;
import ru.tinkoff.edu.java.scrapper.model.Link;

import java.net.URI;
import java.time.OffsetDateTime;
import java.util.Date;
import java.util.List;

public interface LinkRepository {
    Long add(Link link);
    List<Link> findAll();
    boolean remove(Long id);
    boolean removeAll();
    Link findByUrl(URI uri);
    List<Link> findAllBeforeTime(OffsetDateTime time);
    Long update(Link link);
}
