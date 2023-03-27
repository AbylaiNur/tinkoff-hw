package ru.tinkoff.edu.java.linkParser.validators;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ValidLinkValidatorTest {
    @Test
    void validate1() {
        Validator validator = Validator.link(new ValidLinkValidator("lksdff"));
        assertEquals(false, validator.validate());
    }

    @Test
    void validate2() {
        Validator validator = Validator.link(new ValidLinkValidator("https://www.baeldung.com/junit-5"));
        assertEquals(true, validator.validate());
    }

    @Test
    void validate3() {
        Validator validator = Validator.link(new ValidLinkValidator("www.baeldung.com/junit-5"));
        assertEquals(false, validator.validate());

    }

    @Test
    void validate4() {
        Validator validator = Validator.link(new ValidLinkValidator("https://www.google"));
        assertEquals(true, validator.validate());
    }
}
