package ru.otus.l12.encapsulation.demo2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import ru.otus.l12.encapsulation.demo1.Department;

// Неизменяемые коллекции
public class CompanyUnmodifiableDepartments {
    private List<Department> departments = new ArrayList<>();

    public List<Department> getDepartments() {
        return Collections.unmodifiableList(departments);
    }
}
