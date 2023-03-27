package ru.tinkoff.edu.java.linkParser.validators;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MinPathSizeValidatorTest {

    @Test
    void validate1() {
        Validator validator = Validator.link(new MinPathSizeValidator(
                "https://stackoverflow.com/questions/a351237",
                2
        ));
        assertEquals(true, validator.validate());
    }

    @Test
    void validate2() {
        Validator validator = Validator.link(new MinPathSizeValidator(
                "https://stackoverflow.com/questions/",
                2
        ));
        assertEquals(false, validator.validate());
    }
    @Test
    void validate3() {
        Validator validator = Validator.link(new MinPathSizeValidator(
                "https://stackoverflow.com///questions/////",
                2
        ));
        assertEquals(false, validator.validate());
    }
}
