package ru.tinkoff.edu.java.linkParser.parsers;

import ru.tinkoff.edu.java.linkParser.utils.LinkHelper;
import ru.tinkoff.edu.java.linkParser.validators.DomainNameValidator;
import ru.tinkoff.edu.java.linkParser.validators.IncludeSegmentPathNameValidator;
import ru.tinkoff.edu.java.linkParser.validators.IntegerSegmentPathValidator;
import ru.tinkoff.edu.java.linkParser.validators.MinPathSizeValidator;
import ru.tinkoff.edu.java.linkParser.validators.ValidLinkValidator;
import ru.tinkoff.edu.java.linkParser.validators.Validator;
import ru.tinkoff.edu.java.linkParser.validators.ValidatorChainBuilder;

import java.util.HashMap;
import java.util.Map;

public class StackoverflowLinkParser implements LinkParser {
    @Override
    public Map<String, String> parse(String url) {
        if (!canParse(url)) {
            return null;
        }
        Map<String, String> map = new HashMap<>();
        map.put("id", LinkHelper.getPathSegments(url).get(1));
        return map;
    }

    @Override
    public Boolean canParse(String url) {
        ValidatorChainBuilder validatorChainBuilder = new ValidatorChainBuilder(
                new ValidLinkValidator(url),
                new DomainNameValidator(url, "stackoverflow.com"),
                new MinPathSizeValidator(url, 2),
                new IncludeSegmentPathNameValidator(url, 0, "questions"),
                new IntegerSegmentPathValidator(url, 1, 0l, Long.MAX_VALUE)
        );
        Validator validator = validatorChainBuilder.toValidator();
        return validator.validate();
    }
}
