package ru.job4j.pooh.util;

import net.jcip.annotations.Immutable;
import ru.job4j.pooh.queue.Queue;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ConcurrentSkipListSet;

/**
 * @author Egor Geraskin(yegeraskin13@gmail.com)
 * @version 1.0
 * @since 15.01.2021
 */
public final class Topics {
    private final static Topics INSTANCE = new Topics();
    private final ConcurrentLinkedQueue<Queue> queues = new ConcurrentLinkedQueue<>();
    private final Map<String, Object> monitors = new ConcurrentHashMap<>();

    private Topics() {
    }

    public static Topics instOf() {
        return INSTANCE;
    }

    public void addToAllTopicQueues(String theme, String json) {
        Iterator<Queue> it = queues.iterator();
        while (it.hasNext()) {
            Queue queue = it.next();
            queue.offer(theme, json);
        }
    }

    public void addTopicQueue(Queue queue) {
        queues.offer(queue);
    }

    public Object getTopicMonitor(String theme) {
        if (!monitors.containsKey(theme)) {
            monitors.put(theme, new Object());
        }
        return monitors.get(theme);
    }
}
