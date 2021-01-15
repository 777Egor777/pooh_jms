package ru.job4j.pooh.model;

import net.jcip.annotations.Immutable;

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
}
