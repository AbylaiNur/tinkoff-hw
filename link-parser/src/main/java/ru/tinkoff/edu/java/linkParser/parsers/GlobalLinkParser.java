package ru.tinkoff.edu.java.linkParser.parsers;

import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GlobalLinkParser {

    public static Map<String, String> parse(String url) {
        List<LinkParser> parsers = getAllParsers();
        for (LinkParser parser : parsers) {
            if (parser.canParse(url)) {
                return parser.parse(url);
            }
        }
        return null;
    }
    private static List<LinkParser> getAllParsers() {
        List<LinkParser> parsers = new ArrayList<>();

        Reflections reflections = new Reflections("ru.tinkoff.edu.java.linkParser.parsers");
        List<Class<? extends LinkParser>> list = reflections.getSubTypesOf(LinkParser.class)
                .stream()
                .collect(Collectors.toList());
        for (Class<?> clazz : list) {
            try {
                LinkParser parser = (LinkParser) clazz.getDeclaredConstructor().newInstance();
                parsers.add(parser);
            } catch (Exception e) {
                // При ошибке получения instance продолжаем итерировать по классам
            }
        }

        return parsers;
    }
}
