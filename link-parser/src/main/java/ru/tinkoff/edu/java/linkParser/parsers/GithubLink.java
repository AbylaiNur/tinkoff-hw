package ru.tinkoff.edu.java.linkParser.parsers;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Set;

public class GithubLink extends Link {

    public GithubLink(String url) {
        super(url);
    }

    public String[] getUsernameAndRepository() {
        if (!hasUsernameAndRepository()) {
            return null;
        }

        URL url = null;
        try {
            url = new URL(this.url);
        } catch (MalformedURLException e) {
            return null;
        }

        String strPath = url.getPath().substring(1);
        String[] path = strPath.split("/");

        return new String[]{path[0], path[1]};
    }

    public boolean hasUsernameAndRepository() {
        Set<String> set = Set.of("about", "blog", "codespaces", "collections", "contact", "customer-stories", "events", "explore", "features", "issues", "join", "login", "logout", "marketplace", "new", "nonprofit", "notifications", "pricing", "pulls", "security", "settings", "sponsors", "stars", "topics", "trending", "wiki");

        URL url = null;
        try {
            url = new URL(this.url);
            url.toURI();
        } catch (MalformedURLException e) {
            return false;
        } catch (URISyntaxException e) {
            return false;
        }

        if (url.getPath().length() < 2) {
            return false;
        }

        String strPath = url.getPath().substring(1);
        String[] path = strPath.split("/");

        if (url.getHost().equals("github.com") == false) {
            return false;
        }

        if (path.length < 2) {
            return false;
        }

        if (set.contains(path[0])) {
            return false;
        }

        return true;
    }
}
