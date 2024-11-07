package ru.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Creating {
    private static final Logger log = LoggerFactory.getLogger(Creating.class);

    public static void main(String[] args) throws InterruptedException {
        log.info("begin");
        var threadPlatform = Thread.ofPlatform()
                .start(() -> log.info(
                        "do something platform thread. isVirtual:{}",
                        Thread.currentThread().isVirtual()));

        var threadVirtual = Thread.ofVirtual()
                .start(() -> log.info(
                        "do something virtual thread. isVirtual:{}",
                        Thread.currentThread().isVirtual()));
        threadPlatform.join();
        threadVirtual.join();
        log.info("end");
    }
}
