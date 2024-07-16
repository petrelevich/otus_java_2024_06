package demo.generics;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GenericsMethod {
    private static final Logger logger = LoggerFactory.getLogger(GenericsMethod.class);

    public static void main(String[] args) {
        GenericsMethod genericsMethod = new GenericsMethod();
        genericsMethod.print(1, "value");
        genericsMethod.print(2, "value2");
    }

    private <K, V> void print(K key, V val) {
        logger.info("key:{}, val:{}", key, val);
    }
}
