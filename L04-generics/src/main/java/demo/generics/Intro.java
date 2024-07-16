package demo.generics;

import java.time.LocalTime;
import java.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SuppressWarnings({"rawtypes", "unchecked"})
public class Intro {
    private static final Logger logger = LoggerFactory.getLogger(Intro.class);

    public static void main(String[] args) {
        new Intro().beforeGenerics();
        logger.info("-------");
        new Intro().generics();
    }

    // До Generics
    private void beforeGenerics() {
        List list = new ArrayList();
        list.add(4.0);
        list.add(4L);
        list.add("Hello");
        list.add(LocalTime.now());

        for (Object item : list) { // Object !!!
            logger.info("{}", item);
        }
    }

    // Эра Generics
    private void generics() {
        List<Integer> list = new ArrayList<>();
        list.add(4);
        //        list.add(4.0); //ошибка компиляции
        //        list.add(4L);    //ошибка компиляции
        //        list.add("Hello"); //ошибка компиляции
        //        list.add(LocalTime.now()); //ошибка компиляции

        for (int item : list) {
            logger.info("{}", item);
        }
    }
}
