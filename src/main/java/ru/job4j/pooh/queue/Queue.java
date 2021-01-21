package ru.job4j.pooh.queue;

import ru.job4j.pooh.util.Queues;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * @author Egor Geraskin(yegeraskin13@gmail.com)
 * @version 1.0
 * @since 15.01.2021
 */
public class Queue {
    private final Map<String, ConcurrentLinkedQueue<String>> queues = new ConcurrentHashMap<>();

    public void offer(String theme, String json) {
        queues.putIfAbsent(theme, new ConcurrentLinkedQueue<>());
        queues.get(theme).offer(json);
    }

    public final String poll(String theme) {
        String result = null;
        if (queues.containsKey(theme)) {
            result = queues.get(theme).poll();
        }
        return result;
    }
}
