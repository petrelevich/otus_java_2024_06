package ru.otus.thread;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DeadlockDemo {
    private static final Logger logger = LoggerFactory.getLogger(DeadlockDemo.class);

    private final Resource r1 = new Resource("R1");
    private final Resource r2 = new Resource("R2");

    public static void main(String[] args) {
        new DeadlockDemo().demo();
    }

    private void demo() {
        sleep();
        var t1 = new Thread(() -> action(r1, r2));
        t1.setName("t1");

        var t2 = new Thread(() -> action(r2, r1));
        t2.setName("t2");

        t1.start();
        t2.start();

        sleep();
        sleep();
        logger.info("findDeadlockedThreads");
        long[] threads = ManagementFactory.getThreadMXBean().findDeadlockedThreads();
        if (threads != null) {
            ThreadInfo[] threadInfo = ManagementFactory.getThreadMXBean().getThreadInfo(threads);
            logger.atInfo()
                    .setMessage("blocked threads:{}")
                    .addArgument(() -> Arrays.toString(threadInfo))
                    .log();
        }

        // Как решить проблему deadlock?
    }

    @SuppressWarnings({"java:S125", "java:S2445"})
    private static void action(Resource has, Resource need) {
        logger.info("{} has: {}", Thread.currentThread().getName(), has);
        synchronized (has) {
            sleep();
            logger.info("{} taking: {}", Thread.currentThread().getName(), need);
            synchronized (need) {
                logger.info("taken by {}", Thread.currentThread().getName());
                operation(has, need);
            }
        }
    }

    private static void operation(Resource from, Resource to) {
        logger.info("transmition from {} to {}", from, to);
    }

    private static void sleep() {
        try {
            Thread.sleep(TimeUnit.SECONDS.toMillis(10));
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private record Resource(String name) implements Comparable<Resource> {
        @Override
        public String toString() {
            return "Resource{" + "name='" + name + '\'' + '}';
        }

        @Override
        public int compareTo(Resource o) {
            return o.name.compareTo(this.name);
        }
    }
}
