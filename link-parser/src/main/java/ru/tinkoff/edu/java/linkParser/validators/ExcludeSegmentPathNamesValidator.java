package ru.tinkoff.edu.java.linkParser.validators;

import ru.tinkoff.edu.java.linkParser.utils.LinkHelper;

import java.util.List;

public class ExcludeSegmentPathNamesValidator extends Validator {
    private String url;
    private Integer segmentIndex;
    private List<String> invalidPathNames;

    public ExcludeSegmentPathNamesValidator(
        String url,
        Integer segmentIndex,
        List<String> invalidPathNames
    ) {
        this.url = url;
        this.segmentIndex = segmentIndex;
        this.invalidPathNames = invalidPathNames;
    }

    @Override
    public Boolean validate() {
        String segment = LinkHelper.getPathSegments(url).get(segmentIndex);
        if (invalidPathNames.contains(segment)) {
            return false;
        }
        return validateNext();
    }
}
