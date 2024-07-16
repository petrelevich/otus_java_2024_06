package demo.generics;

import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GenericsClass<K, V> {
    private static final Logger logger = LoggerFactory.getLogger(GenericsClass.class);

    private final Map<K, V> map = new HashMap<>();

    public static void main(String[] args) {
        GenericsClass<Integer, String> genericsClass = new GenericsClass<>();
        genericsClass.putVal(1, "data1");
        genericsClass.putVal(2, "data2");
        genericsClass.putVal(3, "data3");

        genericsClass.print();

        GenericsClass<String, String> stringStringGenericsClass = new GenericsClass<>();
        stringStringGenericsClass.putVal("k1", "v1");
        stringStringGenericsClass.putVal("k2", "v2");
        stringStringGenericsClass.putVal("k3", "v3");

        stringStringGenericsClass.print();
    }

    private void putVal(K key, V val) {
        map.put(key, val);
    }

    private void print() {
        map.forEach((key, val) -> logger.info("key:{}, val:{}", key, val));
    }
}
