package demo;

import demo.generics.bounds.entries.Animal;
import demo.generics.bounds.entries.Cat;
import demo.generics.bounds.entries.HomeCat;
import demo.generics.bounds.entries.WildCat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ArrayListDemo {
    private static final Logger logger = LoggerFactory.getLogger(ArrayListDemo.class);

    public static void main(String[] args) {
        Cat[] animalArr = new Cat[] {new HomeCat("Мурзик"), new HomeCat("Васька")};
        List<Cat> animalList = Arrays.asList(animalArr);
        logger.info("animalList {}:", animalList);

        animalArr[0] = new HomeCat("1");
        logger.info("animalList updated{}:", animalList);

        // animalList.add(new HomeCat("Мурка")); //Ошибка

        Animal[] catsArr = animalList.toArray(new Animal[0]);
        logger.atInfo()
                .setMessage("catsArr:{}")
                .addArgument(() -> Arrays.toString(catsArr))
                .log();

        // copy(List<? super T> dest, List<? extends T> src)
        List<? super Cat> animalListDest = new ArrayList<>(animalList);
        Collections.copy(animalListDest, animalList);
        logger.info("homeCats:{}", animalListDest);

        // Как убрать дубли
        List<String> strDubl = Arrays.asList("1", "2", "2", "4");
        logger.info("srtDubl:{}", strDubl);
        Set<String> strDublFiltered = new HashSet<>(strDubl);
        logger.info("strDublFiltered:{}", strDublFiltered);

        // АвтоСортировка
        Set<Integer> sorted = new TreeSet<>();
        sorted.add(1);
        sorted.add(9);
        logger.info("sorted_1:{}", sorted);
        sorted.add(2);
        sorted.add(8);
        logger.info("sorted_2:{}", sorted);

        //
        List<Cat> newCats = new ArrayList<>();
        newCats.add(new WildCat("pantera"));

        List<Cat> superCats = new ArrayList<>(newCats);
        Collections.copy(superCats, newCats);

        WildCat p = (WildCat) superCats.getFirst();
        p.setName("leon");

        logger.info("New Cat:{}", newCats.getFirst());
    }
}
