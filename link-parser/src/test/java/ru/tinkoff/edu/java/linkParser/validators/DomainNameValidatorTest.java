package ru.tinkoff.edu.java.linkParser.validators;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DomainNameValidatorTest {
    @Test
    public void validate1() {
        Validator validator = Validator.link(new DomainNameValidator(
                "https://www.baeldung.com/javax-validation", "www.baeldung.com"
        ));
        assertEquals(true, validator.validate());
    }

    @Test
    public void validate2() {
        Validator validator = Validator.link(new DomainNameValidator(
                "https://www.baeldung.com/javax-validation", "www.aeldung.com"
        ));
        assertEquals(false, validator.validate());
    }
}
