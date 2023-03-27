package ru.tinkoff.edu.java.linkParser.validators;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

public class ValidLinkValidator extends Validator {

    private String url;

    public ValidLinkValidator(String url) {
        this.url = url;
    }

    @Override
    public Boolean validate() {
        try {
            URL url = new URL(this.url);
            url.toURI();
            return validateNext();
        } catch (Exception e) {
            return false;
        }
    }
}
