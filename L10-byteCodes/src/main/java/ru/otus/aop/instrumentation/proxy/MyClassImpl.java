package ru.otus.aop.instrumentation.proxy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyClassImpl {
    private static final Logger logger = LoggerFactory.getLogger(MyClassImpl.class);

    public void secureAccess(String param) {
        logger.info("secureAccess, param:{}", param);
    }

    @Override
    public String toString() {
        return "MyClassImpl{}";
    }
}
