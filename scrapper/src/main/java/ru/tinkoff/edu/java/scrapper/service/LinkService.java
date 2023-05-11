package ru.tinkoff.edu.java.scrapper.service;


import ru.tinkoff.edu.java.scrapper.model.Link;

import java.net.URI;
import java.util.List;

public interface LinkService {
    Link add(long tgChatId, String url);
    Link remove(long tgChatId, String url);
    List<Link> listAll(long tgChatId);
    Link update(Link link);
    List<Link> findAll();
}