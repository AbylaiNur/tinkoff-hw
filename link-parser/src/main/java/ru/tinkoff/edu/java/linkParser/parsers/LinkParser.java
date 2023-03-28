package ru.tinkoff.edu.java.linkParser.parsers;

import java.util.Map;

public interface LinkParser {

    public Map<String, String> parse(String url);

    public Boolean canParse(String url);
}
