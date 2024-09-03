package ru.otus.json.gson;

import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GsonDemo {
    private static final Logger logger = LoggerFactory.getLogger(GsonDemo.class);

    public static void main(String[] args) {
        var gson = new Gson();
        var obj = new BagOfPrimitives(22, "test", 10);
        logger.info("{}", obj);

        String json = gson.toJson(obj);
        logger.info("{}", json);

        BagOfPrimitives obj2 = gson.fromJson(json, BagOfPrimitives.class);
        logger.info("{}", obj.equals(obj2));
        logger.info("{}", obj2);
    }
}
