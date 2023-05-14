package ru.tinkoff.edu.java.linkParser.parsers;

import ru.tinkoff.edu.java.linkParser.utils.LinkHelper;
import ru.tinkoff.edu.java.linkParser.validators.DomainNameValidator;
import ru.tinkoff.edu.java.linkParser.validators.ExcludeSegmentPathNamesValidator;
import ru.tinkoff.edu.java.linkParser.validators.MinPathSizeValidator;
import ru.tinkoff.edu.java.linkParser.validators.ValidLinkValidator;
import ru.tinkoff.edu.java.linkParser.validators.Validator;
import ru.tinkoff.edu.java.linkParser.validators.ValidatorChainBuilder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GithubLinkParser implements LinkParser {

    @Override
    public Map<String, String> parse(String url) {
        if (!canParse(url)) {
            return null;
        }
        Map<String, String> map = new HashMap<>();
        map.put("username", LinkHelper.getPathSegments(url).get(0));
        map.put("repository", LinkHelper.getPathSegments(url).get(1));
        return map;
    }

    @Override
    public Boolean canParse(String url) {
        List<String> invalidPathNames = List.of(
                "about", "blog", "codespaces", "collections", "contact",
                "customer-stories", "events", "explore", "features", "issues",
                "join", "login", "logout", "marketplace", "new", "nonprofit",
                "notifications", "pricing", "pulls", "security", "settings",
                "sponsors", "stars", "topics", "trending", "wiki"
        );

        ValidatorChainBuilder validatorChainBuilder = new ValidatorChainBuilder(
                new ValidLinkValidator(url),
                new DomainNameValidator(url, "github.com"),
                new MinPathSizeValidator(url, 2),
                new ExcludeSegmentPathNamesValidator(url, 0, invalidPathNames)
        );
        Validator validator = validatorChainBuilder.toValidator();
        return validator.validate();
    }
}
