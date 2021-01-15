package ru.job4j.pooh.model.util;

import org.junit.Test;
import ru.job4j.pooh.model.Request;
import ru.job4j.pooh.util.ParseRequest;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class ParseRequestTest {

    @Test
    public void parse() {
        ParseRequest parse = ParseRequest.instOf();
        String json = "{" + System.lineSeparator()
                + "\"queue\": \"" + "weather\"" + System.lineSeparator()
                + "\"text\": \"" + "+18 C\"" + System.lineSeparator() + "}";
        String request = "POST / QUEUE / weather / " + json;
        Request req = parse.parse(request);
        assertThat(req, is(new Request(Request.METHOD_POST,
                Request.MODE_QUEUE, "weather", json)));
    }
}