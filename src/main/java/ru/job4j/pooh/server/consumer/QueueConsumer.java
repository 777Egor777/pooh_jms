package ru.job4j.pooh.server.consumer;

import ru.job4j.pooh.queue.GeneralQueue;
import ru.job4j.pooh.util.Queues;

import java.util.concurrent.Callable;

/**
 * @author Egor Geraskin(yegeraskin13@gmail.com)
 * @version 1.0
 * @since 15.01.2021
 */
public final class QueueConsumer implements Callable<String> {
    private final String theme;

    public QueueConsumer(String theme) {
        this.theme = theme;
    }

    @Override
    public String call() {
        String result = GeneralQueue.instOf().poll(theme);
        return result == null ? "queue is empty" : result;
    }
}
