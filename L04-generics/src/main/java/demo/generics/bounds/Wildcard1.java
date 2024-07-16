package demo.generics.bounds;

import demo.generics.bounds.entries.Animal;
import demo.generics.bounds.entries.Cat;
import demo.generics.bounds.entries.HomeCat;
import demo.generics.bounds.entries.WildCat;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Wildcard1 {
    private static final Logger logger = LoggerFactory.getLogger(Wildcard1.class);

    public static void main(String[] args) {

        List<Animal> animalList = new ArrayList<>();
        animalList.add(new Animal());

        print(animalList);
        printWild(animalList);

        List<Cat> catList = new ArrayList<>();
        catList.add(new Cat());
        catList.add(new HomeCat("Барсик"));
        catList.add(new WildCat("Багира"));

        // print(catList); //Ошибка
        printWild(catList);
    }

    private static void print(List<Animal> animalList) {
        animalList.forEach(animal -> logger.info("{}", animal));
    }

    private static void printWild(List<? extends Animal> animalList) {
        animalList.forEach(animal -> logger.info("{}", animal));
    }
}
