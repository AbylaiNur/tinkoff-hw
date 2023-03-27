package ru.tinkoff.edu.java.linkParser.parsers;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class StackoverflowLinkParserTest {

    @Test
    void hasId1() {
        assertEquals(true, StackoverflowLinkParser.hasId(
                "https://stackoverflow.com/questions/69144581/why-git-merge-remove-existing-line-of-code"
        ));
    }

    @Test
    void hasId2() {
        assertEquals(false, StackoverflowLinkParser.hasId(
                "https://stackoverflow.com/uestions/69144581/why-git-merge-remove-existing-line-of-code"
        ));
    }

    @Test
    void hasId3() {
        assertEquals(false, StackoverflowLinkParser.hasId(
                "https://stackoverflow.com/questions/69aaa581/why-git-merge-remove-existing-line-of-code"
        ));
    }

    @Test
    void hasId4() {
        assertEquals(false, StackoverflowLinkParser.hasId(
                "https://stackoverflow.com/questions/-69581/why-git-merge-remove-existing-line-of-code"
        ));
    }

    @Test
    void hasId5() {
        assertEquals(false, StackoverflowLinkParser.hasId(
                "https://stackoverflow.com/questions//why-git-merge-remove-existing-line-of-code"
        ));
    }

    @Test
    void getId() {
        Map<String, Long> map = new HashMap<String, Long>();
        map.put("id", 100l);
        assertEquals(map, StackoverflowLinkParser.getId(
                "https://stackoverflow.com/questions/100/why-git-merge-remove-existing-line-of-code"
        ));
    }
}
