package ru.job4j.pooh.server.producer;

import ru.job4j.pooh.queue.GeneralQueue;

/**
 * @author Egor Geraskin
 * @version 1.0
 * @since 15.01.2021
 */
public final class QueueProducer implements Runnable {
    private final String theme;
    private final String json;

    public QueueProducer(String theme, String json) {
        this.theme = theme;
        this.json = json;
    }

    @Override
    public void run() {
        GeneralQueue.instOf().offer(theme, json);
    }
}
