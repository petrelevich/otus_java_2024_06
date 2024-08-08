package functionalstyle;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MutationExamples {
    private static final Logger logger = LoggerFactory.getLogger(MutationExamples.class);

    private final List<TestObjectMutable> listMute =
            List.of(new TestObjectMutable(1), new TestObjectMutable(2), new TestObjectMutable(3));
    private final List<TestObjectUnMutable> listUnMute =
            List.of(new TestObjectUnMutable(1), new TestObjectUnMutable(2), new TestObjectUnMutable(3));

    public static void main(String[] args) {
        new MutationExamples().mutableUnMutable();
    }

    private void mutableUnMutable() {
        // Собираем новую коллекцию из старой
        // Плохая идея - менять элементы исходной коллекции, но если коллекция большая, то вариантов
        // может и не быть
        var newList = new ArrayList<TestObjectMutable>();
        for (var elem : listMute) {
            newList.add(elem.updateValue(elem.value - 1));
        }
        logger.info("{}", newList);

        // Лучше так - создаем новый экземпляр, если "надо бы поменять существующий"
        var newList2 = new ArrayList<TestObjectUnMutable>();
        for (var elem : listUnMute) {
            newList2.add(new TestObjectUnMutable(elem.value - 1));
        }
        logger.info("{}", newList2);
    }

    private static class TestObjectMutable {
        private int value;

        public TestObjectMutable(int value) {
            this.value = value;
        }

        public TestObjectMutable updateValue(int value) {
            this.value = value;
            return this;
        }

        @Override
        public String toString() {
            return "TestObjectMutable{" + "value=" + value + '}';
        }
    }

    private record TestObjectUnMutable(int value) {}
}
