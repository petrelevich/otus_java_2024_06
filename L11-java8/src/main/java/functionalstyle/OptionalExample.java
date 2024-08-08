package functionalstyle;

import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SuppressWarnings("java:S125")
public class OptionalExample {
    private static final Logger logger = LoggerFactory.getLogger(OptionalExample.class);

    public static void main(String[] args) {
        var data = Optional.ofNullable(someData());

        var result = data.map(OptionalExample::someFunc1)
                .map(OptionalExample::someFunc2)
                .filter(v -> v < 200)
                .orElseThrow(() -> new RuntimeException("data is null"));

        logger.info("result:{}", result);
    }

    private static Integer someData() {
        // return 50;
        return null;
    }

    // Nullable
    private static Integer someFunc1(Integer value) {
        return 50 + value;
        // return null;
    }

    // Nullable
    private static Integer someFunc2(Integer value) {
        return value * 2;
        // return null;
    }
}
