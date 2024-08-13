package ru.otus.l12.cohesion;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author spv created on 11.02.20.
 */

// Где Cohesion больше: A или B (класс B ниже)?

@SuppressWarnings({"java:S125", "java:S1118"})
class A {
    private static final Logger logger = LoggerFactory.getLogger(A.class);
    private String message;
    // Инициализация message опущена

    public void process() {
        // ...
        send();
    }

    private void send() {
        // ... Здесь может быть какая-то логика...
        logger.info("Send: {}", this.message);
    }
}

@SuppressWarnings({"java:S125", "java:S1118"})
class B {
    private String message;
    // Инициализация message опущена

    //    public  B(String message){
    //        this.message = message;
    //    }
    public void process() {
        // ...
        Helper.send(this.message);
    }

    //    private void send(String message) {
    //        // ... Здесь может быть какая-то логика...
    //        System.out.println(
    //                "Send: " + message);
    //    }
}

// См. еще код ниже

@SuppressWarnings({"java:S125", "java:S1118"})
class Helper {
    private static final Logger logger = LoggerFactory.getLogger(Helper.class);

    public static void send(String message) {
        // ... Здесь может быть какая-то логика...
        logger.info("Send: {}", message);
    }
}
