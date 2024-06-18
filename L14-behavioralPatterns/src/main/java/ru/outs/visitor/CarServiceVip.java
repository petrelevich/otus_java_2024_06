package ru.outs.visitor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CarServiceVip implements Visitor {
    private static final Logger logger = LoggerFactory.getLogger(CarServiceVip.class);

    private static final String MESSAGE = "Дорого и красиво:{}";

    @Override
    public void visit(Engine item) {
        logger.atInfo().setMessage(MESSAGE).addArgument(item::checkEngine).log();
    }

    @Override
    public void visit(Transmission item) {
        logger.atInfo().setMessage(MESSAGE).addArgument(item::refreshOil).log();
    }

    @Override
    public void visit(Brake item) {
        logger.atInfo().setMessage(MESSAGE).addArgument(item::replaceBrakePad).log();
    }
}
