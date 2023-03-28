package ru.tinkoff.edu.java.linkParser.validators;

import ru.tinkoff.edu.java.linkParser.utils.LinkHelper;

public class IntegerSegmentPathValidator extends Validator {
    private String url;
    private Integer segmentIndex;
    private Long minValue;
    private Long maxValue;

    public IntegerSegmentPathValidator(String url, Integer segmentIndex, Long minValue, Long maxValue) {
        this.url = url;
        this.segmentIndex = segmentIndex;
        this.minValue = minValue;
        this.maxValue = maxValue;
    }

    public IntegerSegmentPathValidator(String url, Integer segmentIndex) {
        this.url = url;
        this.segmentIndex = segmentIndex;
        this.minValue = Long.MIN_VALUE;
        this.maxValue = Long.MAX_VALUE;
    }

    @Override
    public Boolean validate() {
        try {
            String strNumber = LinkHelper.getPathSegments(url).get(segmentIndex);
            Long number = Long.valueOf(strNumber);
            if (!(minValue <= number && number <= maxValue)) {
                return false;
            }
            return validateNext();
        } catch (Exception e) {
            return false;
        }
    }
}
