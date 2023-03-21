package ru.tinkoff.edu.java.linkParser.parsers;

public class Link {
    public String url;

    public Link(String url) {
        if (url.charAt(url.length() - 1) == '/') {
            this.url = url.substring(0, url.length() - 1);
        } else {
            this.url = url;
        }
    }
}
