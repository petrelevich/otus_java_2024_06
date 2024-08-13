package ru.otus.l12.encapsulation.demo2;

import java.util.ArrayList;
import java.util.List;
import ru.otus.l12.encapsulation.demo1.Department;

// Сужаем интерфейс
public class CompanyIterableDepartments {
    private List<Department> departments = new ArrayList<>();

    public Iterable<Department> getDepartments() {
        return departments;
    }

    public void addDepartment(Department department) {
        // ... какая-то логика
        departments.add(department);
        // ... какая-то логика
    }
}
