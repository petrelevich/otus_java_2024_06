package ru.outs.memento;

import java.time.LocalDateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Demo {
    private static final Logger logger = LoggerFactory.getLogger(Demo.class);

    public static void main(String[] args) {
        var originator = new Originator(LocalDateTime::now);

        var abc = new State(new String[] {"A", "B", "C"});
        logger.info("{}", abc);

        originator.saveState(abc);
        abc.getArray()[0] = "A1";
        logger.info("{}", abc);

        originator.saveState(abc);
        abc.getArray()[0] = "A2";
        logger.info("{}", abc);

        originator.saveState(abc);
        abc.getArray()[0] = "A3";
        logger.info("{}", abc);

        logger.info("  undo changes");

        abc = originator.restoreState();
        logger.info("{}", abc);

        abc = originator.restoreState();
        logger.info("{}", abc);

        abc = originator.restoreState();
        logger.info("{}", abc);
    }
}
