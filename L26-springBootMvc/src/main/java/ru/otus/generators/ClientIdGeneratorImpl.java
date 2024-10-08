package ru.otus.generators;

import java.util.concurrent.atomic.AtomicLong;
import org.springframework.stereotype.Component;

@Component
public class ClientIdGeneratorImpl implements ClientIdGenerator {

    private static final AtomicLong CLIENT_ID = new AtomicLong(0);

    @Override
    public long generateId() {
        return CLIENT_ID.incrementAndGet();
    }
}
