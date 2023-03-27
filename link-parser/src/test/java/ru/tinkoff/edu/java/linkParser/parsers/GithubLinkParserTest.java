package ru.tinkoff.edu.java.linkParser.parsers;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class GithubLinkParserTest {

    @Test
    void hasUsernameAndRepo1() {
        assertEquals(false, GithubLinkParser.hasUsernameAndRepo("https://github.com/settings/profile"));
    }

    @Test
    void hasUsernameAndRepo2() {
        assertEquals(false, GithubLinkParser.hasUsernameAndRepo("https://github.com"));
    }

    @Test
    void hasUsernameAndRepo3() {
        assertEquals(true, GithubLinkParser.hasUsernameAndRepo("https://github.com/AbylaiNur/tinkoff-hw"));
    }

    @Test
    void hasUsernameAndRepo4() {
        assertEquals(true, GithubLinkParser.hasUsernameAndRepo("https://github.com/AbylaiNur/tinkoff-hw/actions/new"));
    }

    @Test
    void getUsernameAndRepo1() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("username", "AbylaiNur");
        map.put("repository", "tinkoff-hw");
        assertEquals(map, GithubLinkParser.getUsernameAndRepo("https://github.com/AbylaiNur/tinkoff-hw"));
    }

    @Test
    void getUsernameAndRepo2() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("username", "AbylaiNur");
        map.put("repository", "tinkoff-hw");
        assertEquals(map, GithubLinkParser.getUsernameAndRepo("https://github.com/AbylaiNur/tinkoff-hw/actions/new"));
    }

    @Test
    void getUsernameAndRepo3() {
        assertNull(GithubLinkParser.getUsernameAndRepo("https://git.com/AbylaiNur/tinkoff-hw/actions/new"));
    }

    @Test
    void getUsernameAndRepo4() {
        assertNull(GithubLinkParser.getUsernameAndRepo("https://github.com/settings/profile"));
    }
}
