package ru.outs.visitor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CarService implements Visitor {
    private static final Logger logger = LoggerFactory.getLogger(CarService.class);

    @Override
    public void visit(Engine item) {
        logger.atInfo().setMessage("{}").addArgument(item::checkEngine).log();
    }

    @Override
    public void visit(Transmission item) {
        logger.atInfo().setMessage("{}").addArgument(item::refreshOil).log();
    }

    @Override
    public void visit(Brake item) {
        logger.atInfo().setMessage("{}").addArgument(item::replaceBrakePad).log();
    }
}
