package ru.job4j.pooh.util;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author Egor Geraskin
 * @version 1.0
 * @since 15.01.2021
 */
public final class ThreadPool {
    private final ExecutorService pool = Executors.newCachedThreadPool();
    private final static ThreadPool INSTANCE = new ThreadPool();

    private ThreadPool() {
    }

    public static ThreadPool instOf() {
        return INSTANCE;
    }

    public Future<String> submit(Callable<String> task) {
        return pool.submit(task);
    }

    public void execute(Runnable task) {
        pool.execute(task);
    }
}
