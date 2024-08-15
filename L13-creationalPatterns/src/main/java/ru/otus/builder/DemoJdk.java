package ru.otus.builder;

import static java.util.Calendar.MONDAY;

import java.util.Calendar;
import java.util.Locale;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/** Примеры builder в JDK. */
@SuppressWarnings({"java:S1854", "java:S1481"})
public class DemoJdk {
    private static final Logger logger = LoggerFactory.getLogger(DemoJdk.class);

    public static void main(String[] args) {
        // Locale
        Locale locale = new Locale.Builder().setLanguage("ru").setRegion("RU").build();

        // Calendar
        Calendar cal = new Calendar.Builder()
                .setCalendarType("iso8601")
                .setWeekDate(2021, 1, MONDAY)
                .build();

        // StringBuilder (not fluent)
        StringBuilder builder = new StringBuilder();
        builder.append("a");
        builder.append("b");
        builder.append("c");

        String str = builder.toString();

        logger.info("{}", str);

        // StringBuilder (fluent)
        StringBuilder builder2 = new StringBuilder().append("aa ").append("bb ").append("cc");

        String str2 = builder2.toString();

        logger.info("{}", str2);
    }
}
