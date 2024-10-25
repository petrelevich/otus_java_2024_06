package ru.otus.thread;

import java.util.concurrent.TimeUnit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ThreadMethodDemo {
    private static final Logger logger = LoggerFactory.getLogger(ThreadMethodDemo.class);

    public static void main(String[] args) throws InterruptedException {
        logger.info("starting");

        var thread = new Thread(() -> {
            var stop = false;
            while (!stop) {
                logger.info(
                        "I am: {} state: {}",
                        Thread.currentThread().getName(),
                        Thread.currentThread().getState());
                stop = sleepAndStop();
                Thread.onSpinWait();
            }
        });
        thread.setName("Named-thread");
        thread.setDaemon(false);
        logger.info("state: {}", thread.getState());

        thread.start();

        sleep();
        logger.info("interrupting");
        thread.interrupt(); // просим поток остановиться

        thread.join(); // ждем завершения потока thread

        logger.info("state: {}", thread.getState());

        logger.info("finished");
    }

    private static boolean sleepAndStop() {
        try {
            Thread.sleep(TimeUnit.SECONDS.toMillis(1));
            // Если поток получает interrupt() во время sleep(),
            // то бросается InterruptedException
            return false;
        } catch (InterruptedException e) {
            // InterruptedException сдрасывает флаг isInterrupted()
            logger.info(
                    "somebody is trying to stop us, Ok, 1 interrupt = {}",
                    Thread.currentThread().isInterrupted());
            logger.info(
                    "somebody is trying to stop us, Ok, 2 interrupt = {}",
                    Thread.currentThread().isInterrupted());
            // восстанавливаем флаг isInterrupted()
            Thread.currentThread().interrupt();
            logger.info(
                    "somebody is trying to stop us, Ok, 3 interrupt = {}",
                    Thread.currentThread().isInterrupted());
            sleep();
            logger.info(
                    "somebody is trying to stop us, Ok, 4 interrupt = {}",
                    Thread.currentThread().isInterrupted());
            return true;
        }
    }

    private static void sleep() {
        try {
            Thread.sleep(TimeUnit.SECONDS.toMillis(3));
        } catch (InterruptedException e) {
            logger.info("InterruptedException in sleep");
            Thread.currentThread().interrupt();
        }
    }
}
