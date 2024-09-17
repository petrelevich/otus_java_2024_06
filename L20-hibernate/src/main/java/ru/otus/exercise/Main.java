package ru.otus.exercise;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.core.HibernateUtils;
import ru.otus.exercise.model.*;

public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        try (var sessionFactory =
                HibernateUtils.buildSessionFactory(OtusStudent.class, Avatar.class, EMail.class, Course.class)) {
            logger.info("Statistics:{}", sessionFactory.getStatistics());
        }
    }
}
