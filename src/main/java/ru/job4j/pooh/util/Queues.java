package ru.job4j.pooh.util;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Egor Geraskin(yegeraskin13@gmail.com)
 * @version 1.0
 * @since 15.01.2021
 */
public final class Queues {
    private final static Queues INSTANCE = new Queues();
    private final Map<String, Object> monitors = new ConcurrentHashMap<>();

    private Queues() {
    }

    public static Queues instOf() {
        return INSTANCE;
    }

    public Object getQueueMonitor(String theme) {
        if (!monitors.containsKey(theme)) {
            monitors.put(theme, new Object());
        }
        return monitors.get(theme);
    }
}
