package ru.tinkoff.edu.java.linkParser.parsers;

import ru.tinkoff.edu.java.linkParser.utils.LinkHelper;
import ru.tinkoff.edu.java.linkParser.validators.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public record GithubLinkParser() {
    public static Map<String, String> getUsernameAndRepo(String url) {
        if (!hasUsernameAndRepo(url)) {
            return null;
        }
        Map<String, String> map = new HashMap<>();
        map.put("username", LinkHelper.getPathSegments(url).get(0));
        map.put("repository", LinkHelper.getPathSegments(url).get(1));
        return map;
    }

    public static Boolean hasUsernameAndRepo(String url) {
        List<String> invalidPathNames = List.of(
                "about", "blog", "codespaces", "collections", "contact",
                "customer-stories", "events", "explore", "features", "issues",
                "join", "login", "logout", "marketplace", "new", "nonprofit",
                "notifications", "pricing", "pulls", "security", "settings",
                "sponsors", "stars", "topics", "trending", "wiki"
        );

        Validator link = Validator.link(
                new ValidLinkValidator(url),
                new DomainNameValidator(url, "github.com"),
                new MinPathSizeValidator(url, 2),
                new ExcludeSegmentPathNamesValidator(url, 0, invalidPathNames)
        );

        return link.validate();
    }
}
