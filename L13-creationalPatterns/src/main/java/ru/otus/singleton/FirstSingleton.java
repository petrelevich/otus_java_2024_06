package ru.otus.singleton;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FirstSingleton {
    private static final Logger logger = LoggerFactory.getLogger(FirstSingleton.class);
    private static final FirstSingleton instance = new FirstSingleton();

    // ! private constructor
    private FirstSingleton() {
        logger.info("run constructor");
    }

    // public API
    public static FirstSingleton getInstance() {
        return instance;
    }
}

@SuppressWarnings({"java:S3457"})
class FirstSingletonDemo {
    private static final Logger logger = LoggerFactory.getLogger(FirstSingletonDemo.class);

    public static void main(String[] args) {
        logger.info("--- begin ---");

        FirstSingleton singleton1 = FirstSingleton.getInstance();
        FirstSingleton singleton2 = FirstSingleton.getInstance();

        logger.info("{}", singleton1);
        logger.info("{}", singleton2);

        logger.info("singleton1 == singleton2 => {}\n", singleton1 == singleton2);
        logger.info("---end ---");
    }
}
