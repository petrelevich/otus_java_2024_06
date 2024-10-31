package ru.otus.thread;

import java.util.concurrent.TimeUnit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * В VisualVM статусы потоков не соответствуют Thread.State
 * @see Thread.State
 *
 * BLOCKED -> Monitor
 * RUNNABLE -> Running
 * WAITING/TIMED_WAITING -> Sleeping/Park/Wait
 * TERMINATED/NEW -> Zombie
 *
 * https://stackoverflow.com/a/36449178
 */
public class ThreadMonitoringDemo {
    private static final Logger logger = LoggerFactory.getLogger(ThreadMonitoringDemo.class);

    public static void main(String[] args) {
        var thread = new Thread(() -> {
            while (true) {
                logger.info("Hello from {}", Thread.currentThread().getName());
                sleep();
            }
        });
        thread.setName("Thread-Monitoring-Demo");
        thread.start();
    }

    private static void sleep() {
        try {
            Thread.sleep(TimeUnit.SECONDS.toMillis(10));
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
