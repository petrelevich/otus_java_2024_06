package ru.outs.memento;

import java.util.ArrayDeque;
import java.util.Deque;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class Originator {
    private static final Logger logger = LoggerFactory.getLogger(Originator.class);
    // Фактически, это stack
    private final Deque<Memento> stack = new ArrayDeque<>();

    private final DateTimeProvider dateTimeProvider;

    public Originator(DateTimeProvider dateTimeProvider) {
        this.dateTimeProvider = dateTimeProvider;
    }

    void saveState(State state) {
        stack.push(new Memento(state, dateTimeProvider.getDate()));
    }

    State restoreState() {
        var memento = stack.pop();
        logger.info("createdAt:{}", memento.createdAt());
        return memento.state();
    }
}
