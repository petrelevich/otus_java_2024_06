package demo.generics.bounds;

import demo.generics.bounds.entries.Cat;
import demo.generics.bounds.entries.HomeCat;
import demo.generics.bounds.entries.WildCat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GenericsBounded<T extends Cat> {
    private static final Logger logger = LoggerFactory.getLogger(GenericsBounded.class);

    private final T cat;

    public GenericsBounded(T cat) {
        this.cat = cat;
    }

    public static void main(String[] args) {

        // GenericsBounded<Animal> genericsBounded = new GenericsBounded<>(); //ошибка
        GenericsBounded<Cat> cat = new GenericsBounded<>(new Cat());
        GenericsBounded<HomeCat> homeCat = new GenericsBounded<>(new HomeCat("Barsik"));
        GenericsBounded<WildCat> wildCat = new GenericsBounded<>(new WildCat("Maul"));

        cat.action();
        homeCat.action();
        wildCat.action();
    }

    private void action() {
        String actionResult = cat.getMyau();
        logger.info("actionResult:{}", actionResult);
    }
}
