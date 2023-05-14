package ru.tinkoff.edu.java.linkParser.validators;

import ru.tinkoff.edu.java.linkParser.utils.LinkHelper;

import java.util.Objects;

public class IncludeSegmentPathNameValidator extends Validator {
    private String url;
    private Integer segmentIndex;
    private String validPathName;

    public IncludeSegmentPathNameValidator(
        String url,
        Integer segmentIndex,
        String validPathName
    ) {
        this.url = url;
        this.segmentIndex = segmentIndex;
        this.validPathName = validPathName;
    }

    @Override
    public Boolean validate() {
        String segment =
            LinkHelper.getPathSegments(url).get(segmentIndex);
        if (!Objects.equals(validPathName, segment)) {
            return false;
        }
        return validateNext();
    }
}
