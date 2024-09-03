package ru.otus.dataprocessor;

import java.util.List;
import java.util.Map;
import ru.otus.model.Measurement;

public interface Processor {

    Map<String, Double> process(List<Measurement> data);
}
