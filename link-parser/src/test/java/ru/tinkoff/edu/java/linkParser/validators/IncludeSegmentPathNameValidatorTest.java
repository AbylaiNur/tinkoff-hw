package ru.tinkoff.edu.java.linkParser.validators;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class IncludeSegmentPathNameValidatorTest {

    @Test
    void validate1() {
        Validator validator = Validator.link(new IncludeSegmentPathNameValidator(
                "https://stackoverflow.com/questions/3351237/data-type-for-storing-urls",
                0,
                "questions"
        ));
        assertEquals(true, validator.validate());
    }

    @Test
    void validate2() {
        Validator validator = Validator.link(new IncludeSegmentPathNameValidator(
                "https://stackoverflow.com/questions/3351237/data-type-for-storing-urls",
                0,
                "question"
        ));
        assertEquals(false, validator.validate());
    }

    @Test
    void validate3() {
        Validator validator = Validator.link(new IncludeSegmentPathNameValidator(
                "https://stackoverflow.com/questions/3351237/data-type-for-storing-urls",
                1,
                "3351237"
        ));
        assertEquals(true, validator.validate());
    }
}
