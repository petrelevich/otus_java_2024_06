package ru.otus.flyweight.cow;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SmartList {
    private static final Logger logger = LoggerFactory.getLogger(SmartList.class);

    private Data data;

    public SmartList() {
        this.data = new Data();
        this.data.dataValues = new ArrayList<>();
        this.data.users = 1;
    }

    private SmartList(Data data) {
        this.data = data;
        data.users += 1;
    }

    public int get(int n) {
        return data.dataValues.get(n);
    }

    public int size() {
        return data.dataValues.size();
    }

    public void add(int v) {
        if (data.users > 1) {
            data.users -= 1;
            data = data.copy();
        }
        data.dataValues.add(v);
    }

    public SmartList copy() {
        return new SmartList(data);
    }

    private static class Data {
        private List<Integer> dataValues;
        private int users;

        Data copy() {
            var res = new Data();
            res.dataValues = new ArrayList<>(dataValues);
            res.users = 1;
            return res;
        }
    }

    public static void main(String[] args) {
        var a = new SmartList();
        a.add(1);
        a.add(2);

        var b = a.copy();
        b.get(0);
        b.add(3);

        logger.info("{}", a.size());
        logger.info("{}", b.size());
    }
}
