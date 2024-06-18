package ru.outs.observer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Demo {
    private static final Logger logger = LoggerFactory.getLogger(Demo.class);

    public static void main(String[] args) {
        var producer = new EventProducer();
        var consumerA = new ConsumerA();
        var consumerB = new ConsumerB();

        boolean[] first = {true};

        Listener temp = data -> {
            logger.info("Temp listener:{}", data);
            if (first[0]) {
                first[0] = false;
                producer.event("EventB");
            }
        };

        producer.addListener(temp);
        producer.addListener(consumerA);
        producer.addListener(consumerB.getListener());

        producer.event("eventA");
        producer.event("eventB");

        producer.removeListener(temp);

        // Критически важно удалять, когда не нужны
        producer.removeListener(consumerA);
        producer.removeListener(consumerB.getListener());

        producer.event("eventC");
        producer.event("eventD");
    }
}
