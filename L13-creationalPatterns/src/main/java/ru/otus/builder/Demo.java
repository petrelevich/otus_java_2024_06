package ru.otus.builder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Demo {
    private static final Logger logger = LoggerFactory.getLogger(Demo.class);

    public static void main(String[] args) {
        // Так плохо - не понятно, что за параметры
        var employee1 = new Employee("Иванов", null, null, "A Corp", null, "Java-разработчик");
        logger.info("{}", employee1);

        // Так лучше
        var employee2 = new Employee.Builder("Петров", "ABC Corp")
                .department("IT") // задаем нужные параметры
                .firstName("Иван") // в любом порядке
                // .position("...") // для необязательных просто не вызываем метод
                .middleName("Иванович")
                .build(); // получаем нужный нам объект

        logger.info("{}", employee2);

        // Использование lombok
        var employee3 = EmployeeLombokBuilder.builder()
                .lastName("Сидоров")
                .company("ABC Corp")
                .department("IT")
                .middleName("Иванович")
                .build();

        var copiedEmployee3 = employee3.toBuilder().position("Developer").build();

        logger.info("lombok: {}", employee3);
        logger.info("copy: {}", copiedEmployee3);
    }
}
