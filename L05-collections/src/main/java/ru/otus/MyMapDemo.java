package ru.otus;

import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SuppressWarnings("java:S106")
public class MyMapDemo {
    private static final Logger logger = LoggerFactory.getLogger(MyMapDemo.class);

    public static void main(String[] args) {

        var mapSize = 200_000;
        var keyStr = "k";
        ///////////

        long sum1 = 0;
        Map<String, Integer> hashMap = HashMap.newHashMap(mapSize);
        long begin = System.currentTimeMillis();

        for (var idx = 0; idx < mapSize; idx++) {
            hashMap.put(keyStr + idx, idx);
        }

        for (var idx = 0; idx < mapSize; idx++) {
            sum1 += hashMap.get(keyStr + idx);
        }
        logger.info("HashMap time:{}", (System.currentTimeMillis() - begin));

        ////
        logger.info("-----");
        long sum2 = 0;
        var myMap = new MyMapInt(mapSize);
        begin = System.currentTimeMillis();

        for (var idx = 0; idx < mapSize; idx++) {
            myMap.put(keyStr + idx, idx);
        }

        for (var idx = 0; idx < mapSize; idx++) {
            sum2 += myMap.get(keyStr + idx);
        }
        logger.info("MyMapInt time:{}", (System.currentTimeMillis() - begin));
        logger.info("sum1:{}, sum2:{}", sum1, sum2);
    }
}
