package ru.otus.flyweight;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author sergey
 * created on 16.01.19.
 */
public class Demo {
    private static final Logger logger = LoggerFactory.getLogger(Demo.class);

    public static void main(String[] args) {
        var objectFactory = new ObjectFactory();

        var object1 = objectFactory.create(1);
        logger.info("{}", object1);

        var object2 = objectFactory.create(2);
        logger.info("{}", object2);

        var object3 = objectFactory.create(3);
        logger.info("{}", object3);
    }
}
