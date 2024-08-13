package ru.otus.l12.solid;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author sergey created on 09.09.19.
 */
@SuppressWarnings({"java:S1144", "java:S2094"})
public class OpenClosed {
    private static final Logger logger = LoggerFactory.getLogger(OpenClosed.class);

    // Плохой пример
    // Эту функцию без модификации не получится использовать, например, с TreeSet и другим
    // алгоритмом
    private void messageProcessing(ArrayList<Message> messageList) {
        messageList.forEach(msg -> logger.info(msg.toString()));
    }

    // Хороший пример
    private void messageProcessing(Collection<Message> messageList, Processor<Message> processor) {
        messageList.forEach(processor::action);
    }

    // применение хорошего примера
    // messageProcessing можно использовать без изменений
    void good() {

        // использование 1 (вызов, например, из другого класса)
        messageProcessing(new HashSet<>(), msg -> logger.info(msg.toString()));

        // использование 2 (вызов, например, из другого класса)
        messageProcessing(new ArrayList<>(), new Processor2());
    }

    static class Processor2 implements Processor<Message> {

        @Override
        public void action(Message msg) {
            logger.info("{} R", msg);
        }
    }

    @FunctionalInterface
    private interface Processor<T> {
        void action(T msg);
    }

    private static class Message {}
}
