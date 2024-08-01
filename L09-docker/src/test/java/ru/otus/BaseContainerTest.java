package ru.otus;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;

public abstract class BaseContainerTest {
    private static final Logger log = LoggerFactory.getLogger(BaseContainerTest.class);
    private static final PostgreSQLContainer<?> POSTGRE_SQL_CONTAINER = new PostgreSQLContainer<>("postgres:16");

    static {
        POSTGRE_SQL_CONTAINER.start();
    }

    @DynamicPropertySource
    public static void properties(DynamicPropertyRegistry registry) {
        log.info("POSTGRE_SQL_CONTAINER.getJdbcUrl:{}", POSTGRE_SQL_CONTAINER.getJdbcUrl());
        registry.add("database.url", () -> POSTGRE_SQL_CONTAINER.getJdbcUrl() + "&stringtype=unspecified");
        registry.add("database.user", POSTGRE_SQL_CONTAINER::getUsername);
        registry.add("database.pwd", POSTGRE_SQL_CONTAINER::getPassword);
    }
}
