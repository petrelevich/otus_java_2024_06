package demo.generics.bounds;

import demo.generics.bounds.entries.Animal;
import demo.generics.bounds.entries.Cat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SuppressWarnings({"unchecked", "rawtypes"})
public class Wildcard2 {
    private static final Logger logger = LoggerFactory.getLogger(Wildcard2.class);

    public static void main(String[] args) {

        List<Animal> animalList = new ArrayList<>();
        animalList.add(new Animal());
        printWild(animalList);
        printObj(animalList);

        List<Cat> catList = new ArrayList<>();
        catList.add(new Cat());
        printWild(catList);
        printObj(catList);

        // левый тип данных
        List<String> stringList = new ArrayList<>();
        stringList.add("подкидыш");
        printWild(stringList);
        printObj(stringList);

        // Можно еще и так
        List voidList = new ArrayList<>();
        voidList.add(LocalTime.now());
        printWild(voidList);
        printObj(voidList);
    }

    // Unbounded Wildcards
    private static void printWild(List<?> animalList) {
        // animalList.add("внезапно добавленная строка"); //ошибка
        // animalList.add(new Animal()) //ошибка
        animalList.forEach(animal -> logger.info("{}", animal));
    }

    private static void printObj(List animalList) {
        animalList.add("внезапно добавленная строка");
        animalList.forEach(animal -> logger.info("{}", animal));
    }
}
