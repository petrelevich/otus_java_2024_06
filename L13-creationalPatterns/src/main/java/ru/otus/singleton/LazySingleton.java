package ru.otus.singleton;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SuppressWarnings({"java:S1192"})
public class LazySingleton {
    private static final Logger logger = LoggerFactory.getLogger(LazySingleton.class);
    private static LazySingleton instance = null;

    private LazySingleton() {
        logger.info("run constructor");
    }

    public static LazySingleton getInstance() {
        if (instance == null) {
            // плохо, может быть гонка и мы можем получить два синглтона
            logger.info("lazy init 1");
            instance = new LazySingleton();
        }

        return instance;
    }

    @SuppressWarnings({"java:S1192", "java:S4144", "java:S2168"})
    public static synchronized LazySingleton getInstance2() {
        // ок, но медленно
        if (instance == null) {
            logger.info("lazy init 2");
            instance = new LazySingleton();
        }

        return instance;
    }

    @SuppressWarnings("java:S2168")
    public static LazySingleton getInstance3() {
        // сложно и не работает - см
        // https://www.cs.umd.edu/~pugh/java/memoryModel/DoubleCheckedLocking.html
        if (instance == null) {
            synchronized (LazySingleton.class) {
                if (instance == null) {
                    logger.info("lazy init 3");
                    instance = new LazySingleton();
                }
            }
        }

        return instance;
    }
}

@SuppressWarnings({"java:S3457"})
class LazySingletonDemo {
    private static final Logger logger = LoggerFactory.getLogger(LazySingletonDemo.class);

    public static void main(String[] args) {
        logger.info("--- begin ---");

        LazySingleton singleton1 = LazySingleton.getInstance();
        LazySingleton singleton2 = LazySingleton.getInstance();

        logger.info("{}", singleton1);
        logger.info("{}", singleton2);
        logger.info("singleton1 == singleton2 => {}\n", singleton1 == singleton2);
        logger.info("---end ---");
    }
}
