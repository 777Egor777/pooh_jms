package ru.job4j.pooh.queue;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class GeneralQueueTest {

    @Test
    public void instOf() {
        GeneralQueue queue = GeneralQueue.instOf();
        queue.offer("a", "b");
        String result = queue.poll("a");
        assertThat(result, is("b"));
    }
}