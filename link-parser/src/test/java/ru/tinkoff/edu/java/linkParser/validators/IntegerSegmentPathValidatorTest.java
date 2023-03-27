package ru.tinkoff.edu.java.linkParser.validators;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class IntegerSegmentPathValidatorTest {

    @Test
    void validate1() {
        Validator validator = Validator.link(new IntegerSegmentPathValidator(
                "https://stackoverflow.com/questions/3351237/data-type-for-storing-urls",
                1
        ));
        assertEquals(true, validator.validate());
    }

    @Test
    void validate2() {
        Validator validator = Validator.link(new IntegerSegmentPathValidator(
                "https://stackoverflow.com/questions/a351237/data-type-for-storing-urls",
                1
        ));
        assertEquals(false, validator.validate());
    }

    @Test
    void validate3() {
        Validator validator = Validator.link(new IntegerSegmentPathValidator(
                "https://stackoverflow.com/questions/100/data-type-for-storing-urls",
                1,
                0l,
                100l
        ));
        assertEquals(true, validator.validate());
    }
    @Test
    void validate4() {
        Validator validator = Validator.link(new IntegerSegmentPathValidator(
                "https://stackoverflow.com/questions/100/data-type-for-storing-urls",
                1,
                0l,
                99l
        ));
        assertEquals(false, validator.validate());
    }
    @Test
    void validate5() {
        Validator validator = Validator.link(new IntegerSegmentPathValidator(
                "https://stackoverflow.com/questions/-100/data-type-for-storing-urls",
                1,
                0l,
                100l
        ));
        assertEquals(false, validator.validate());
    }
}
