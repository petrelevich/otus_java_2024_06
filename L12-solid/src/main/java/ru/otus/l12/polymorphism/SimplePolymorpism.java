package ru.otus.l12.polymorphism;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SuppressWarnings({"rawtypes", "java:S125"})
public class SimplePolymorpism {
    private static final Logger logger = LoggerFactory.getLogger(SimplePolymorpism.class);

    public static void main(String[] args) {
        // Плохо
        ArrayList list1 = new ArrayList();
        LinkedList list2 = new LinkedList();

        doSomethingWithListBad(list1);
        doSomethingWithListBad(list2);

        // Хорошо
        List list3 = new ArrayList();
        List list4 = new LinkedList();
        // List list5 = new DIYArrayList();

        doSomethingWithListGood(list3);
        doSomethingWithListGood(list4);
    }

    private static void doSomethingWithListBad(ArrayList list) {
        logger.info("doSomethingWithListBad(ArrayList list)");
        for (var item : list) {
            logger.info("{}", item);
        }
    }

    private static void doSomethingWithListBad(LinkedList list) {
        logger.info("doSomethingWithListBad(LinkedList list)");
        for (var item : list) {
            logger.info("{}", item);
        }
    }

    private static void doSomethingWithListGood(List list) {
        logger.info("doSomethingWithListGood(List list)");
        for (var item : list) {
            logger.info("{}", item);
        }
    }
}
