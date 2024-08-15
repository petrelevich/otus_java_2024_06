package ru.otus.singleton;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author sergey created on 19.09.18.
 */
@SuppressWarnings({"java:S3457", "java:S2440"})
public class Singleton {
    private static final Logger logger = LoggerFactory.getLogger(Singleton.class);

    private Singleton() {
        logger.info("lazy creation");
    }

    static Singleton getInstance() {
        logger.info("getInstance");
        return SingletonHolder.instance;
    }

    private static class SingletonHolder {
        static {
            logger.info("init SingletonHolder");
        }

        static final Singleton instance = new Singleton();
    }
}

@SuppressWarnings({"java:S3457"})
class SingletonDemo {
    private static final Logger logger = LoggerFactory.getLogger(SingletonDemo.class);

    public static void main(String[] args) {
        logger.info("--- begin ---");
        logger.info("- singleton 1");
        Singleton singleton1 = Singleton.getInstance();

        logger.info("");
        logger.info("- singleton 2");
        Singleton singleton2 = Singleton.getInstance();

        logger.info("");
        logger.info("singleton1 == singleton2 => {}\n", singleton1 == singleton2);
        logger.info("{}", singleton1);
        logger.info("{}", singleton2);
        logger.info("---end ---");
    }
}
