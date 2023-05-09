package ru.tinkoff.edu.java.linkParser.utils;

import java.net.URL;
import java.util.List;

public record LinkHelper() {
    public static String getPath(String link) {
        try {
            URL url = new URL(link);
            String strPath = url.getPath();

            int startIndex;
            int endIndex;
            for (startIndex = 0; startIndex < strPath.length(); startIndex++) {
                if (strPath.charAt(startIndex) != '/') {
                    break;
                }
            }
            for (endIndex = strPath.length() - 1; endIndex >= 0; endIndex--) {
                if (strPath.charAt(endIndex) != '/') {
                    break;
                }
            }

            if (startIndex >= endIndex) {
                return null;
            }
            return strPath.substring(startIndex, endIndex + 1);
        } catch (Exception e) {
            return null;
        }
    }
    public static List<String> getPathSegments(String link) {
        String strPath = getPath(link);
        List<String> pathSegments = List.of(strPath.split("/"));
        return pathSegments;
    }

    public static String getHost(String link) {
        try {
            URL url = new URL(link);
            return url.getHost();
        } catch (Exception e) {
            return null;
        }
    }

    public static Integer getPathSize(String link) {
        return getPathSegments(link).size();
    }
}
