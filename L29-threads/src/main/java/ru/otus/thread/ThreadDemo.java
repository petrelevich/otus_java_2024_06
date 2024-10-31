package ru.otus.thread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ThreadDemo {
    private static final Logger logger = LoggerFactory.getLogger(ThreadDemo.class);

    public static void main(String[] args) {
        case1();
        case2();

        // Когда какой способ использовать?
    }

    /**
     * Создание потока через передачу Runnable
     */
    private static void case1() {
        // В логах видим имя потока
        logger.info("{}. Main program started (case1)", Thread.currentThread().getName());

        var thread = new Thread(() ->
                logger.info("from thread (case1): {}", Thread.currentThread().getName()));
        // Выполняется асинхронно
        thread.start(); // Просьба к JVM запустить поток

        logger.info("{}. Main program finished (case1)", Thread.currentThread().getName());
    }

    /**
     * Создание потока через наследование Thread
     */
    private static void case2() {
        logger.info("{}. Main program started (case2)", Thread.currentThread().getName());

        var thread = new CustomThread();
        thread.start();

        logger.info("{}. Main program finished (case2)", Thread.currentThread().getName());
    }

    private static class CustomThread extends Thread {
        @Override
        public void run() {
            logger.info("from thread (case2): {}", Thread.currentThread().getName());
        }
    }
}
