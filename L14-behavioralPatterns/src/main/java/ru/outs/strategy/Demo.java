package ru.outs.strategy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Demo {
    private static final Logger logger = LoggerFactory.getLogger(Demo.class);

    public static void main(String[] args) {
        var context = new Context();
        context.setStrategy(new Bus());
        context.applyStrategy();

        context.setStrategy(new Car());
        context.applyStrategy();

        context.setStrategy(new Taxi());
        context.applyStrategy();

        context.setStrategy(() -> logger.info("rocket"));
        context.applyStrategy();
    }
}
