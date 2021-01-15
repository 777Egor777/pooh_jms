package ru.job4j.pooh.server.producer;

import ru.job4j.pooh.util.Topics;

/**
 * @author Egor Geraskin
 * @version 1.0
 * @since 15.01.2021
 */
public final class TopicProducer implements Runnable {
    private final String theme;
    private final String json;

    public TopicProducer(String theme, String json) {
        this.theme = theme;
        this.json = json;
    }

    @Override
    public void run() {
        Topics.instOf().addToAllTopicQueues(theme, json);
    }
}
