package ru.tinkoff.edu.java.linkParser.parsers;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

public class StackoverflowLink extends Link {

    public StackoverflowLink(String url) {
        super(url);
    }

    public String getId() {
        if (!hasId()) {
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

        return path[1];
    }

    public boolean hasId() {
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

        if (url.getHost().equals("stackoverflow.com") == false) {
            return false;
        }

        if (path.length < 2) {
            return false;
        }

        if (!path[0].equals("questions")) {
            return false;
        }

        try {
            if (Integer.parseInt(path[1]) < 0) {
                return false;
            }
        } catch (NumberFormatException e) {
            return false;
        }

        return true;
    }
}
