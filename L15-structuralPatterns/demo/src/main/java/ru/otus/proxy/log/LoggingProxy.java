package ru.otus.proxy.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.proxy.security.SecurityProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class LoggingHandler implements InvocationHandler {

    private static final Logger logger = LoggerFactory.getLogger(LoggingHandler.class);

    private final Object subject;

    private LoggingHandler(Object subject) {
        this.subject = subject;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        logger.info("Invoke method: " + method.getName() + ": " + Arrays.toString(args));

        try {
            var res = method.invoke(subject, args);
            logger.info("method result: " + res);
            return res;
        } catch (Throwable e) {
            logger.error("Exception:", e);
            throw e;
        }
    }

    public static <T> T wrap(Object subject, Class cls) {
        return (T) Proxy.newProxyInstance(LoggingProxy.class.getClassLoader(), new Class[] {cls}, new LoggingHandler(subject));
    }
}

public class LoggingProxy {
    public static void main(String[] args) {
        var map = LoggingHandler.<Map<Integer, String>>wrap(new HashMap<Integer, String>(), Map.class);
        map.put(42, "Hello");
        map.get(42);
    }
}
