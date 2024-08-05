package ru.otus.aop.instrumentation.changer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/*
java -javaagent:summatorDemo.jar -jar summatorDemo.jar
*/
public class SummatorDemo {
    private static final Logger logger = LoggerFactory.getLogger(SummatorDemo.class);

    public static void main(String[] args) {
        var anyClass = new AnyClass();
        logger.info("{}", anyClass.summator(30, 20));
    }
}
