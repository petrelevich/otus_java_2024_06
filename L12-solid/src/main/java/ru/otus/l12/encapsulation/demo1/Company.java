package ru.otus.l12.encapsulation.demo1;

import java.util.ArrayList;
import java.util.List;

public class Company {
    String name;
    private final List<Department> departments = new ArrayList<>(); // Ассоциация 1-*

    public String getName() {
        return this.name;
    }

    public List<Department> getDepartments() {

        return departments;
    }

    public void addDepartment(Department department) {
        department.company = this;
        departments.add(department);
    }
}
