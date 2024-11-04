package ru.otus.locks;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ReentrantLockDemo {
    private static final Logger logger = LoggerFactory.getLogger(ReentrantLockDemo.class);

    private final Lock lock = new ReentrantLock();

    public static void main(String[] args) throws InterruptedException {
        new ReentrantLockDemo().go();
    }

    private void go() throws InterruptedException {
        var t1 = new Thread(this::criticalSection);
        t1.setName("t1");
        t1.start();

        var t2 = new Thread(this::criticalSectionDoubleLock);
        t2.setName("t2");
        t2.start();

        t1.join();
        t2.join();
    }

    private void criticalSection() {
        logger.info("1. before critical section");
        lock.lock();
        try {
            logger.info("1. in the critical section");
            sleep();
        } finally {
            logger.info("1. unlock");
            lock.unlock();
        }
        logger.info("1. after critical section");
    }

    private void criticalSectionDoubleLock() {
        logger.info("2. before critical section");
        lock.lock();
        logger.info("2. reentrant into locked section");
        lock.lock();
        try {
            logger.info("2. in the critical section");
            sleep();
        } finally {
            // сколько раз заблокировали, столько надо и разблокировать
            lock.unlock();
            lock.unlock();
        }
        logger.info("2. after critical section");
    }

    private static void sleep() {
        try {
            Thread.sleep(TimeUnit.SECONDS.toMillis(3));
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
