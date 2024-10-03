package ru.otus.services;

import java.util.List;
import ru.otus.model.Equation;

public interface EquationPreparer {
    List<Equation> prepareEquationsFor(int base);
}
