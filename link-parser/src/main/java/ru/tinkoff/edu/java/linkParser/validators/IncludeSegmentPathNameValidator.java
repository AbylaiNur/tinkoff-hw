package ru.tinkoff.edu.java.linkParser.validators;

import ru.tinkoff.edu.java.linkParser.utils.LinkHelper;

import java.util.Objects;

public class IncludeSegmentPathNameValidator extends Validator {
    private String url;
    private Integer segmentIndex;
    private String validPathName;

    public IncludeSegmentPathNameValidator(String url, Integer segmentIndex, String validPathName) {
        this.url = url;
        this.segmentIndex = segmentIndex;
        this.validPathName = validPathName;
    }

    @Override
    public Boolean validate() {
        if (!Objects.equals(validPathName, LinkHelper.getPathSegments(url).get(segmentIndex))) {
            return false;
        }
        return validateNext();
    }
}
