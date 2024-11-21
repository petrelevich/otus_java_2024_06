package ru.demo;

import static java.time.temporal.ChronoUnit.MILLIS;

import java.time.Duration;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.ReentrantLock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JumpThreadLock {
    private static final Logger log = LoggerFactory.getLogger(JumpThreadLock.class);

    public static void main(String[] args) throws InterruptedException {
        Thread.ofVirtual().start(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                sleep(1);
            }
        });

        var iterationCounter = new AtomicLong(0);
        var lock = new ReentrantLock();
        var thread = Thread.ofVirtual().start(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                iterationCounter.incrementAndGet();

                try {
                    lock.lock();

                    var firstThread = Thread.currentThread().toString();
                    sleep(10);
                    var secondThread = Thread.currentThread().toString();
                    if (!Objects.equals(firstThread, secondThread)) {
                        log.info("1_thread:{}", firstThread);
                        log.info("2_thread:{}", secondThread);
                        Thread.currentThread().interrupt();
                    } else {
                        if (iterationCounter.get() > 1000) {
                            Thread.currentThread().interrupt();
                            log.info("break loop");
                        }
                    }
                } finally {
                    lock.unlock();
                }
            }
        });
        thread.join();
        log.info("iterationCounter:{}", iterationCounter);
    }

    private static void sleep(long ms) {
        try {
            Thread.sleep(Duration.of(ms, MILLIS));
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
