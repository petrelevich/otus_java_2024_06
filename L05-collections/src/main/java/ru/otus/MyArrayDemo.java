package ru.otus;

import java.util.ArrayList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyArrayDemo {
    private static final Logger logger = LoggerFactory.getLogger(MyArrayDemo.class);

    public static void main(String[] args) throws Exception {
        var arraySizeMax = 1_000_000;
        var arraySizeInit = 10;
        ///////////
        long sum1 = 0;
        try (var myArr = new MyArrayInt(arraySizeInit)) {
            var begin = System.currentTimeMillis();

            for (var idx = 0; idx < arraySizeMax; idx++) {
                myArr.setValue(idx, idx);
            }

            for (var idx = 0; idx < arraySizeMax; idx++) {
                sum1 += myArr.getValue(idx);
            }
            logger.info("myArr time:{}", (System.currentTimeMillis() - begin));
        }
        ////
        logger.info("-----");
        long sum2 = 0;
        var arrayList = new ArrayList<Integer>(arraySizeInit);
        var begin = System.currentTimeMillis();

        for (var idx = 0; idx < arraySizeMax; idx++) {
            arrayList.add(idx, idx);
        }

        for (var idx = 0; idx < arraySizeMax; idx++) {
            sum2 += arrayList.get(idx);
        }
        logger.info("ArrayList time:{}", (System.currentTimeMillis() - begin));
        logger.info("sum1:{}, sum2:{} ", sum1, sum2);
    }
}
