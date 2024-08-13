package ru.otus.l12.encapsulation.demo2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.l12.encapsulation.demo1.Department;

public class EncapsusationDemo2 {
    private static final Logger logger = LoggerFactory.getLogger(EncapsusationDemo2.class);

    public static void main(String[] args) {
        var company1 = new CompanyUnmodifiableDepartments();
        var departments1 = company1.getDepartments();

        try {
            departments1.add(new Department());
        } catch (Exception ex) {
            logger.error("error", ex);
        }

        var company2 = new CompanyIterableDepartments();
        company2.addDepartment(new Department());
        company2.addDepartment(new Department());
        var departments2 = company2.getDepartments();

        for (Department department : departments2) {
            logger.info("{}", department);
        }
    }
}
