package ru.otus.proxy.log;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class LoggingHandler implements InvocationHandler {

    private static final Logger logger = LoggerFactory.getLogger(LoggingHandler.class);

    private final Object subject;

    private LoggingHandler(Object subject) {
        this.subject = subject;
    }

    @Override
    @SuppressWarnings("java:S2139")
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        logger.info("Invoke method:{} : {}", method.getName(), args);

        try {
            var res = method.invoke(subject, args);
            logger.info("method result: {}", res);
            return res;
        } catch (Exception e) {
            logger.error("Exception:", e);
            throw e;
        }
    }

    public static <T> T wrap(Object subject, Class<?> cls) {
        return (T) Proxy.newProxyInstance(
                LoggingProxy.class.getClassLoader(), new Class<?>[] {cls}, new LoggingHandler(subject));
    }
}

public class LoggingProxy {
    private static final Logger logger = LoggerFactory.getLogger(LoggingProxy.class);

    public static void main(String[] args) {
        var map = LoggingHandler.<Map<Integer, String>>wrap(new HashMap<Integer, String>(), Map.class);
        map.put(42, "Hello");
        var value = map.get(42);
        logger.info("value: {}", value);
    }
}
