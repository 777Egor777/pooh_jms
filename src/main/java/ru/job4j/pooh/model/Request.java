package ru.job4j.pooh.model;

import net.jcip.annotations.Immutable;

import java.util.Objects;

/**
 * @author Egor Geraskin
 * @version 1.0
 * @since 15.01.2021
 */
@Immutable
public final class Request {
    public final static int METHOD_GET = 0;
    public final static int METHOD_POST = 1;
    public final static int MODE_QUEUE = 0;
    public final static int MODE_TOPIC = 1;

    private final int method;
    private final int mode;
    private final String theme;
    private final String json;

    public Request(int method, int mode, String theme, String json) {
        this.method = method;
        this.mode = mode;
        this.theme = theme;
        this.json = json;
    }

    public int getMethod() {
        return method;
    }

    public int getMode() {
        return mode;
    }

    public String getTheme() {
        return theme;
    }

    public String getJson() {
        return json;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Request request = (Request) o;
        return method == request.method
                && mode == request.mode
                && Objects.equals(theme, request.theme)
                && Objects.equals(json, request.json);
    }

    @Override
    public int hashCode() {
        return Objects.hash(method, mode, theme, json);
    }

    @Override
    public String toString() {
        return String.format("Request{%d, %d, %s, %s}",
                method, mode, theme, json);
    }
}
