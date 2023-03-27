package ru.tinkoff.edu.java.linkParser.validators;

import ru.tinkoff.edu.java.linkParser.utils.LinkHelper;

import java.util.Objects;

public class DomainNameValidator extends Validator {

    private String url;
    private String domainName;

    public DomainNameValidator(String url, String domainName) {
        this.url = url;
        this.domainName = domainName;
    }


    @Override
    public Boolean validate() {
        if (!Objects.equals(domainName, LinkHelper.getHost(url))) {
            return false;
        }
        return validateNext();
    }
}
