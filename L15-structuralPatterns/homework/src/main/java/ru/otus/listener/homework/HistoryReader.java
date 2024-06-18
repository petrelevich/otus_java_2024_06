package ru.otus.listener.homework;

import java.util.Optional;
import ru.otus.model.Message;

public interface HistoryReader {

    Optional<Message> findMessageById(long id);
}
