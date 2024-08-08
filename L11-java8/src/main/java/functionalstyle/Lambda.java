package functionalstyle;

import java.util.function.Function;
import java.util.function.IntSupplier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Lambda {
    private static final Logger logger = LoggerFactory.getLogger(Lambda.class);

    private String value;

    public static void main(String[] args) {
        var lambda = new Lambda();

        var result = lambda.func(str -> str + "+mod", "testStr");
        logger.info(result);

        var result2 = lambda.func(val -> val * 10, 5);
        logger.info("{}", result2);

        // "Билдер" экземпляров Lambda с инициализацией поля value константой
        var l = lambda.func(
                lb -> {
                    lb.value = "testValue";
                    return lb;
                },
                new Lambda());
        logger.info(l.value);

        // int[] initValue - не поле инстанса или класса, но сохраняет свое значение между вызовами
        // функции
        // Так работает, но ТАК ДЕЛАТЬ НЕ НАДО.
        var closure = lambda.generator();
        logger.info("1:{}", closure.getAsInt());
        logger.info("2:{}", closure.getAsInt());
        logger.info("3:{}", closure.getAsInt());
    }

    private <T, R> R func(Function<T, R> func, T param) {
        return func.apply(param);
    }

    private IntSupplier generator() {
        int[] initValue = {0}; // Переменная не только effectively final, но и effectively private :)
        return () -> ++initValue[0];
    }
}
