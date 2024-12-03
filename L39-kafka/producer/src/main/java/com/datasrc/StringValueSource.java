package com.datasrc;

import com.datasrc.model.StringValue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Consumer;

public class StringValueSource implements ValueSource {
    private final AtomicLong nextValue = new AtomicLong(1);

    private final Consumer<StringValue> valueConsumer;
    private final ScheduledExecutorService executor;

    public StringValueSource(ScheduledExecutorService executor, Consumer<StringValue> valueConsumer) {
        this.valueConsumer = valueConsumer;
        this.executor = executor;
    }

    @Override
    public void generate() {
        executor.scheduleAtFixedRate(() -> valueConsumer.accept(makeValue()), 0, 1, TimeUnit.SECONDS);
    }

    private StringValue makeValue() {
        var id = nextValue.getAndIncrement();
        return new StringValue(id, "stVal:" + id);
    }
}
