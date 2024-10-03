package ru.otus;

import ru.otus.services.EquationPreparer;
import ru.otus.services.EquationPreparerImpl;
import ru.otus.services.GameProcessor;
import ru.otus.services.GameProcessorImpl;
import ru.otus.services.IOService;
import ru.otus.services.IOServiceStreams;
import ru.otus.services.PlayerService;
import ru.otus.services.PlayerServiceImpl;

@SuppressWarnings({"squid:S125", "squid:S106"})
public class App {
    public static void main(String[] args) {
        EquationPreparer equationPreparer = new EquationPreparerImpl();
        IOService ioService = new IOServiceStreams(System.out, System.in); // NOSONAR
        PlayerService playerService = new PlayerServiceImpl(ioService);
        GameProcessor gameProcessor = new GameProcessorImpl(ioService, equationPreparer, playerService);

        // ApplicationContext ctx = new ClassPathXmlApplicationContext("/spring-context.xml");
        // ApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
        // GameProcessor gameProcessor = ctx.getBean(GameProcessor.class);

        gameProcessor.startGame();
    }
}
