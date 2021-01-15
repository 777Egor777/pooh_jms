package ru.job4j.pooh.queue;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class QueueTest {

    @Test
    public void offer() {
        Queue queue = new Queue();
        queue.offer("a", "b");
        String result = queue.poll("a");
        assertThat(result, is("b"));
    }
}