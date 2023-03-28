package ru.tinkoff.edu.java.linkParser.parsers;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class GithubLinkParserTest {

    @Test
    void hasUsernameAndRepo1() {
        assertEquals(false, new GithubLinkParser().canParse("https://github.com/settings/profile"));
    }

    @Test
    void hasUsernameAndRepo2() {
        assertEquals(false, new GithubLinkParser().canParse("https://github.com"));
    }

    @Test
    void hasUsernameAndRepo3() {
        assertEquals(true, new GithubLinkParser().canParse("https://github.com/AbylaiNur/tinkoff-hw"));
    }

    @Test
    void hasUsernameAndRepo4() {
        assertEquals(true, new GithubLinkParser().canParse("https://github.com/AbylaiNur/tinkoff-hw/actions/new"));
    }

    @Test
    void getUsernameAndRepo1() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("username", "AbylaiNur");
        map.put("repository", "tinkoff-hw");
        assertEquals(map, new GithubLinkParser().parse("https://github.com/AbylaiNur/tinkoff-hw"));
    }

    @Test
    void getUsernameAndRepo2() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("username", "AbylaiNur");
        map.put("repository", "tinkoff-hw");
        assertEquals(map, new GithubLinkParser().parse("https://github.com/AbylaiNur/tinkoff-hw/actions/new"));
    }

    @Test
    void getUsernameAndRepo3() {
        assertNull(new GithubLinkParser().parse("https://git.com/AbylaiNur/tinkoff-hw/actions/new"));
    }

    @Test
    void getUsernameAndRepo4() {
        assertNull(new GithubLinkParser().parse("https://github.com/settings/profile"));
    }
}
