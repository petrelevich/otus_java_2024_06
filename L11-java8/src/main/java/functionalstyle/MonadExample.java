package functionalstyle;

import java.util.ArrayList;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SuppressWarnings({"java:S1192"})
public class MonadExample {
    private static final Logger logger = LoggerFactory.getLogger(MonadExample.class);

    public static void main(String[] args) {
        var monadExample = new MonadExample();

        var result = monadExample.function("test");
        logger.info(result);

        result = monadExample.function(null);
        logger.info(result);

        result = monadExample.functionWrong(null);
        logger.info(result);

        logger.info("------------------");
        result = monadExample.functionNorm("functionNorm");
        logger.info(result);

        result = monadExample.functionNorm(null);
        logger.info(result);
    }

    private String function(String str) {
        return Optional.ofNullable(str)
                .map(val -> "!" + val.toUpperCase())
                .map(param -> param + "+addStr")
                .orElse("param is NULL");
    }

    // некорректное использование монады
    private String functionWrong(String str) {
        Optional<String> optional = anyFunction(str);

        if (optional.isPresent()) {
            return optional.get() + "+addStr";
        }
        return "param is NULL";
    }

    // но так уже нормально
    private String functionNorm(String str) {
        var list = new ArrayList<String>();
        var listUpper = new ArrayList<String>();

        Optional<String> optional = anyFunction(str);

        if (optional.isPresent()) {
            list.add(optional.get());
            listUpper.add(optional.get().toUpperCase());

            logger.info("{}", list);
            logger.info("{}", listUpper);

            return optional.get() + "+addStr";
        }
        return "param is NULL";
    }

    // типовое применение - возврат значения, которое может быть null
    private Optional<String> anyFunction(String val) {
        return Optional.ofNullable(val);
    }
}
