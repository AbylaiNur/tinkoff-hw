package ru.tinkoff.edu.java.linkParser.validators;

import ru.tinkoff.edu.java.linkParser.utils.LinkHelper;

public class MinPathSizeValidator extends Validator {
    private String url;
    private Integer minPathSize;

    public MinPathSizeValidator(String url, Integer minPathSize) {
        this.url = url;
        this.minPathSize = minPathSize;
    }

    @Override
    public Boolean validate() {
        if (!(minPathSize <= LinkHelper.getPathSize(url))) {
            return false;
        }
        return validateNext();
    }
}
