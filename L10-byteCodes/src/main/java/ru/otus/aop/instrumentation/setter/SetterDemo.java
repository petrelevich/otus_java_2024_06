package ru.otus.aop.instrumentation.setter;

import java.lang.reflect.InvocationTargetException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/*
    java -javaagent:setterDemo.jar -jar setterDemo.jar
*/
public class SetterDemo {
    private static final Logger logger = LoggerFactory.getLogger(SetterDemo.class);

    public static void main(String[] args) throws Exception {
        logger.info("main");
        var demo = new MyClass();
        logger.info("{}", demo.getValue());
        modifyPrivateValue(demo);
        logger.info("{}", demo.getValue());
    }

    private static void modifyPrivateValue(MyClass demo)
            throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        demo.getClass().getMethod("setValue", int.class).invoke(demo, -4);
    }
}
