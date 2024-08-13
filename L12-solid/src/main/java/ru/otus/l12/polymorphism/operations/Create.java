package ru.otus.l12.polymorphism.operations;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.l12.polymorphism.Operation;

/**
 * @author sergey created on 09.09.19.
 */
public class Create implements Operation {
    private static final Logger logger = LoggerFactory.getLogger(Create.class);

    @Override
    public void action(String data) {
        logger.info("create, data:{}", data);
    }
}
