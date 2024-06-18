package ru.outs.chain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

abstract class ApplicationProcessor {
    private static final Logger logger = LoggerFactory.getLogger(ApplicationProcessor.class);
    private ApplicationProcessor next;

    private ApplicationProcessor getNext() {
        return next;
    }

    void setNext(ApplicationProcessor next) {
        this.next = next;
    }

    void process(Application application) {
        before();
        processInternal(application);
        after();
        if (getNext() != null) {
            getNext().process(application);
        }
    }

    protected abstract void processInternal(Application application);

    public abstract String getProcessorName();

    private void before() {
        logger.info("before:{}", getProcessorName());
    }

    private void after() {
        logger.info("after:{}", getProcessorName());
    }
}
