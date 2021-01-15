package ru.job4j.pooh.queue;

import ru.job4j.pooh.util.Queues;

/**
 * @author Egor Geraskin(yegeraskin13@gmail.com)
 * @version 1.0
 * @since 15.01.2021
 */
public class GeneralQueue extends Queue {
    private static final GeneralQueue INSTANCE = new GeneralQueue();

    private GeneralQueue() {
    }

    public static GeneralQueue instOf() {
        return INSTANCE;
    }
}
