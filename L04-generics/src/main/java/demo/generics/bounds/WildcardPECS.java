package demo.generics.bounds;

import demo.generics.bounds.entries.Animal;
import demo.generics.bounds.entries.Cat;
import demo.generics.bounds.entries.HomeCat;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WildcardPECS {
    private static final Logger logger = LoggerFactory.getLogger(WildcardPECS.class);

    public static void main(String[] args) {

        List<Animal> animalList = new ArrayList<>();
        animalList.add(new Animal());
        // printProducer(animalList); //ошибка
        printConsumer(animalList);

        List<Cat> catList = new ArrayList<>();
        catList.add(new Cat());
        printProducer(catList);
        printConsumer(catList);

        logger.info("-------------");
        List<HomeCat> homeCatList = new ArrayList<>();
        homeCatList.add(new HomeCat("homeCat"));
        printProducer(homeCatList);
        //        printConsumer(homeCatList); //ошибка

    }

    private static void printProducer(List<? extends Cat> catList) {
        // catList.add(new Object()); //Ошибка
        // catList.add(new Animal()); //Ошибка
        // catList.add(new Cat()) //Ошибка
        // catList.add(new HomeCat("f")); //Ошибка

        catList.forEach(cat -> logger.info("Myau:{}", cat.getMyau()));
    }

    private static void printConsumer(List<? super Cat> catList) {
        // catList.add(new Object());// Ошибка
        // catList.add(new Animal());// Ошибка
        catList.add(new Cat());
        catList.add(new HomeCat("noName"));

        Object item = catList.getFirst();
        logger.info("item from the list:{}", item);

        catList.forEach(something -> logger.info("{}", something));
    }
}
