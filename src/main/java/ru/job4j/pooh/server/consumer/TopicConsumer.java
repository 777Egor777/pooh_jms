package ru.job4j.pooh.server.consumer;

import ru.job4j.pooh.queue.Queue;
import ru.job4j.pooh.util.Topics;

import java.util.concurrent.Callable;

/**
 * @author Egor Geraskin(yegeraskin13@gmail.com)
 * @version 1.0
 * @since 15.01.2021
 */
public final class TopicConsumer implements Callable<String> {
    private final String theme;
    private final Queue queue;

    public TopicConsumer(String theme, Queue queue) {
        this.theme = theme;
        this.queue = queue;
    }

    @Override
    public String call() {
        String result = queue.poll(theme);
        return result == null ? "queue is empty" : result;
    }
}
