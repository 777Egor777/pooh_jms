package ru.job4j.pooh.util;

import ru.job4j.pooh.model.Request;

/**
 * @author Egor Geraskin
 * @version 1.0
 * @since 15.01.2021
 */
public final class ParseRequest {
    private final static ParseRequest INSTANCE = new ParseRequest();
    
    private ParseRequest() {
    }

    public static ParseRequest instOf() {
        return INSTANCE;
    }

    public Request parse(String request) {
        String[] parts = request.split("/");
        int method = parseMethod(parts[0]);
        int mode = parseMode(parts[1]);
        String theme = parts[2].trim().toLowerCase();
        String json = parts.length >= 4 ? parts[3].trim() : "";
        return new Request(method, mode, theme, json);
    }

    private int parseMethod(String method) {
        method = method.trim().toLowerCase();
        int result;
        if (method.equals("get")) {
            result = Request.METHOD_GET;
        } else if (method.equals("post")) {
            result = Request.METHOD_POST;
        } else {
            throw new IllegalArgumentException("Illegal name of method(nor get or post)");
        }
        return result;
    }

    private int parseMode(String mode) {
        mode = mode.trim().toLowerCase();
        int result;
        if (mode.equals("queue")) {
            result = Request.MODE_QUEUE;
        } else if (mode.equals("topic")) {
            result = Request.MODE_TOPIC;
        } else {
            throw new IllegalArgumentException("Illegal name of mode(nor queue or topic)");
        }
        return result;
    }
}
