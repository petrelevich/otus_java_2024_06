package ru.otus.mainpackage.commandlinerunners;

import java.util.Arrays;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import ru.otus.Messager;

@Component
public class PreparationDev implements CommandLineRunner {
    private static final Logger logger = LoggerFactory.getLogger(PreparationDev.class);

    private final Messager messager;

    public PreparationDev(Messager messager) {
        this.messager = messager;
    }

    @Override
    public void run(String... args) {
        var argsAsString = Arrays.toString(args);
        logger.info("DEV mode!!! Что-то настравиваем и подготавливаем, параметры: {} ", argsAsString);
        var msg = messager.sayMessage();
        logger.info("message from Messager:{}", msg);
        // args парметры, котрые могут быть переданы в Main
    }
}
