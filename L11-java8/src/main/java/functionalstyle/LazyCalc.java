package functionalstyle;

import static java.lang.Thread.sleep;

import java.util.Date;
import java.util.function.IntUnaryOperator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LazyCalc {
    private static final Logger logger = LoggerFactory.getLogger(LazyCalc.class);

    public static void main(String[] args) {
        var calc = new LazyCalc();

        var startTime = new Date().getTime();
        logger.info("== FIRST VARIANT == ");
        calc.calculation(true, calc.veryHeavyFunc(60));
        var endTime = new Date().getTime();
        logger.info("== FIRST, true time : {}", (endTime - startTime));
        startTime = endTime;
        calc.calculation(false, calc.veryHeavyFunc(60));
        endTime = new Date().getTime();
        logger.info("== FIRST, true time : {}", (endTime - startTime));
        startTime = endTime;

        logger.info("== SECOND VARIANT == ");
        calc.calculation(true, 60, calc::veryHeavyFunc);
        endTime = new Date().getTime();
        logger.info("==  true time : {}", (endTime - startTime));
        startTime = endTime;
        calc.calculation(false, 61, calc::veryHeavyFunc);
        endTime = new Date().getTime();
        logger.info("== true time : {}", (endTime - startTime));
    }

    private void calculation(boolean variable, int value, IntUnaryOperator veryHeavyFunc) {
        if (variable) {
            logger.info("some actions");
        } else {
            logger.info("{}:", veryHeavyFunc.applyAsInt(value));
        }
    }

    private void calculation(boolean variable, Integer veryHeavyFunc) {
        if (variable) {
            logger.info("some actions");
        } else {
            logger.info("{}:", veryHeavyFunc);
        }
    }

    private Integer veryHeavyFunc(Integer input) {
        try {
            sleep(1000L);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return input * 2;
    }
}
