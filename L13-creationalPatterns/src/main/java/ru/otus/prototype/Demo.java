package ru.otus.prototype;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Demo {
    private static final Logger logger = LoggerFactory.getLogger(Demo.class);

    public static void main(String[] args) throws CloneNotSupportedException {
        copyExample();
        cloneExample();
    }

    /** Custom copy() */
    private static void copyExample() {
        logger.info("======== copyExample ========");
        CopyableSheep original = new CopyableSheep("unknown");

        CopyableSheep copied = original.copy();
        copied.setName("Dolly");

        logger.info("original = {}", original);
        logger.info("copied = {}", copied);
        logger.info("original.getName() = {}", original.getName());
        logger.info("copied.getName() = {}", copied.getName());
        logger.info("");
    }

    /**
     * Стандартный clone()
     *
     * @throws CloneNotSupportedException
     */
    private static void cloneExample() throws CloneNotSupportedException {
        logger.info("======== cloneExample ========");

        ClonableSheep original = new ClonableSheep("unknown");

        ClonableSheep cloned = original.clone();
        cloned.setName("Dolly");

        logger.info("original = {}", original);
        logger.info("cloned = {}", cloned);
        logger.info("original.getName() = {}", original.getName());
        logger.info("cloned.getName() = {}", cloned.getName());
        logger.info("");
    }
}
