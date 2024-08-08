package lambda;

import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/** author: Dmitry Arkhangelskiy Изобретаем свои лямбды */
@SuppressWarnings({"WeakerAccess", "SameParameterValue", "java:S1854", "java:S4276", "java:S1612", "java:S1481"})
public class MapReduceExample {
    private static final Logger logger = LoggerFactory.getLogger(MapReduceExample.class);

    // Integer -> Double
    static class SquareRoot implements Function<Integer, Double> {
        @Override
        public Double apply(Integer val) {
            return Math.sqrt(val);
        }
    }

    static Double mySqrt(Integer val) {
        return Math.sqrt(val);
    }

    // Трансформация каждого элемента
    static <T, R> Collection<R> map(Collection<T> src, Function<T, R> op) {
        List<R> r = new ArrayList<>();
        for (T t : src) {
            r.add(op.apply(t));
        }
        return r;
    }

    static <T, R> R reduce(Collection<T> src, BiFunction<T, R, R> op, R zero) {
        R result = zero;
        for (T t : src) {
            result = op.apply(t, result);
        }
        return result;
    }

    static <R> Collection<R> filter(Collection<R> src, Predicate<R> pred) {
        List<R> r = new ArrayList<>();
        for (R t : src) {
            if (pred.test(t)) r.add(t);
        }
        return r;
    }

    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(4, 16, 25);
        logger.info("{}", map(list, new SquareRoot()));

        logger.info("{}", map(list, e -> Math.sqrt(e)));

        logger.info("{}", map(list, (Function<Integer, Double>) Math::sqrt));

        logger.info("{}", reduce(list, (v1, v2) -> v1 + v2, 0));

        logger.info("{}", filter(list, a -> a % 2 == 0));

        Function<Integer, Double> func1 = v -> mySqrt(v);
        // mySqrt() is Integer -> Double so it can be referenced as Function<Double, Integer>
        Function<Integer, Double> func2 = MapReduceExample::mySqrt;
    }
}
