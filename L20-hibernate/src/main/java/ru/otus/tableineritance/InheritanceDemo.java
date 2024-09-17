package ru.otus.tableineritance;

import jakarta.persistence.TypedQuery;
import java.sql.SQLException;
import java.util.List;
import org.h2.tools.Console;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.core.HibernateUtils;
import ru.otus.tableineritance.model.A;
import ru.otus.tableineritance.model.B;
import ru.otus.tableineritance.model.C;

public class InheritanceDemo {
    private static final Logger logger = LoggerFactory.getLogger(InheritanceDemo.class);
    public static final String HIBERNATE_CFG_FILE = "hibernate.cfg.xml";

    public static void main(String[] args) throws SQLException {
        try (var sf = HibernateUtils.buildSessionFactory(HIBERNATE_CFG_FILE, A.class, B.class, C.class)) {
            logger.info("\n\n-------------------------------------------\n\n");
            logger.info("Начинаем вставку сущностей A/B/C: ");
            try (var session = sf.openSession()) {

                var a = new A(0, "aaaaaa1");
                var b = new B(0, "aaaaaa2", "bbbbbbb");
                var c = new C(0, "aaaaaa3", "ccccccc");

                session.getTransaction().begin();
                session.persist(a);
                session.persist(b);
                session.persist(c);
                session.getTransaction().commit();

                logger.info("\n\n-------------------------------------------\n\n");
                logger.info("Загружаем все сущности A (в т.ч. наследников):");
                session.getTransaction().begin();
                TypedQuery<A> query = session.createQuery("select fieldA from A a", A.class);
                List<A> resultList = query.getResultList();

                logger.info("\n\nРезультат:{}", resultList);
                session.getTransaction().commit();
            }
            Console.main();
        }
    }
}
