package ru.otus.solution8;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.core.HibernateUtils;
import ru.otus.solution8.model.Avatar;
import ru.otus.solution8.model.Course;
import ru.otus.solution8.model.EMail;
import ru.otus.solution8.model.OtusStudent;

public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        try (var sessionFactory =
                HibernateUtils.buildSessionFactory(OtusStudent.class, Avatar.class, EMail.class, Course.class)) {
            logger.info("Statistics:{}", sessionFactory.getStatistics());
        }
    }
}
