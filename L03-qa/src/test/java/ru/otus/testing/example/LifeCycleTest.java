package ru.otus.testing.example;

import org.junit.jupiter.api.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SuppressWarnings({"java:S2699"})
class LifeCycleTest {
    private static final Logger logger = LoggerFactory.getLogger(LifeCycleTest.class);

    // Подготовительные мероприятия. Метод выполнится один раз, перед всеми тестами
    @BeforeAll
    public static void globalSetUp() {
        System.out.println("@BeforeAll");
    }

    // Подготовительные мероприятия. Метод выполнится перед каждым тестом
    @BeforeEach
    public void setUp() {
        logger.info("\n@BeforeEach. ");
        logger.info("Экземпляр тестового класса: {}", Integer.toHexString(hashCode()));
    }

    // Сам тест
    @Test
    void anyTest1() {
        logger.info("@Test: anyTest1. ");
        logger.info("Экземпляр тестового класса: {}", Integer.toHexString(hashCode()));
    }

    // Еще тест
    @Test
    void anyTest2() {
        logger.info("@Test: anyTest2. ");
        logger.info("Экземпляр тестового класса: {}", Integer.toHexString(hashCode()));
    }

    // Завершающие мероприятия. Метод выполнится после каждого теста
    @AfterEach
    public void tearDown() {
        logger.info("@AfterEach. ");
        logger.info("Экземпляр тестового класса: {}", Integer.toHexString(hashCode()));
    }

    // Завершающие мероприятия. Метод выполнится один раз, после всех тестов
    @AfterAll
    public static void globalTearDown() {
        logger.info("\n@AfterAll");
    }
}
