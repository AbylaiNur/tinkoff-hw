package ru.tinkoff.edu.java.linkParser.parsers;

import ru.tinkoff.edu.java.linkParser.utils.LinkHelper;
import ru.tinkoff.edu.java.linkParser.validators.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StackoverflowLinkParser {

    public static Map<String, Long> getId(String url) {
        if (!hasId(url)) {
            return null;
        }
        Map<String, Long> map = new HashMap<>();
        map.put("id", Long.valueOf(LinkHelper.getPathSegments(url).get(1)));
        return map;
    }

    public static Boolean hasId(String url) {
        Validator link = Validator.link(
                new ValidLinkValidator(url),
                new DomainNameValidator(url, "stackoverflow.com"),
                new MinPathSizeValidator(url, 2),
                new IncludeSegmentPathNameValidator(url, 0, "questions"),
                new IntegerSegmentPathValidator(url, 1, 0l, Long.MAX_VALUE)
        );

        return link.validate();
    }
}
