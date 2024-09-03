package ru.otus.dataprocessor;

import java.util.Collections;
import java.util.List;
import ru.otus.model.Measurement;

public class ResourcesFileLoader implements Loader {

    public ResourcesFileLoader(String fileName) {}

    @Override
    public List<Measurement> load() {
        // читает файл, парсит и возвращает результат
        return Collections.emptyList();
    }
}
