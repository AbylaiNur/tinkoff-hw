package ru.tinkoff.edu.java.linkParser.parsers;

import java.util.Map;

public interface LinkParser {

    Map<String, String> parse(String url);

    Boolean canParse(String url);
}
