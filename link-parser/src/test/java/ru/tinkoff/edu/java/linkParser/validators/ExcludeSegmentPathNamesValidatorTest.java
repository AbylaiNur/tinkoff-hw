package ru.tinkoff.edu.java.linkParser.validators;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExcludeSegmentPathNamesValidatorTest {
    @Test
    public void validate1() {
        Validator validator = Validator.link(new ExcludeSegmentPathNamesValidator(
                "https://github.com/settings/profile", 0, List.of("settings")
        ));
        assertEquals(false, validator.validate());
    }

    @Test
    public void validate2() {
        Validator validator = Validator.link(new ExcludeSegmentPathNamesValidator(
                "https://github.com/settings/profile", 1, List.of("profile")
        ));
        assertEquals(false, validator.validate());
    }

    @Test
    public void validate3() {
        Validator validator = Validator.link(new ExcludeSegmentPathNamesValidator(
                "https://github.com/settings/profile", 0, List.of("random")
        ));
        assertEquals(true, validator.validate());
    }
}
