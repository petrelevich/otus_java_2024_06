package ru.otus.l12.encapsulation.demo3;

import ru.otus.l12.encapsulation.demo1.Company;

@SuppressWarnings({"java:S1854", "java:S1481"})
public class BetterDepartmentDemo {

    public static void main(String[] args) {
        Company company = new Company();
        BetterDepartment department = new BetterDepartment(null);
    }
}
