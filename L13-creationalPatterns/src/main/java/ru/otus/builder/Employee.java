package ru.otus.builder;

@SuppressWarnings("java:S125")
public class Employee {
    private String lastName;
    private String firstName;
    private String middleName;
    private String company;
    private String department;
    private String position;

    // Плохой конструктор.
    public Employee(
            String lastName, String firstName, String middleName, String company, String department, String position) {
        if (lastName == null || lastName.isEmpty()) throw new IllegalArgumentException("lastName is null or empty");
        if (company == null || company.isEmpty()) throw new IllegalArgumentException("company is null or empty");

        this.lastName = lastName;
        this.firstName = firstName;
        this.middleName = middleName;
        this.company = company;
        this.department = department;
        this.position = position;
    }

    private Employee(Builder builder) {
        this.lastName = builder.lastName;
        this.firstName = builder.firstName;
        this.middleName = builder.middleName;
        this.company = builder.company;
        this.department = builder.department;
        this.position = builder.position;
    }

    @Override
    public String toString() {
        return "Employee{" + "lastName='"
                + lastName + '\'' + ", firstName='"
                + firstName + '\'' + ", middleName='"
                + middleName + '\'' + ", company='"
                + company + '\'' + ", department='"
                + department + '\'' + ", position='"
                + position + '\'' + '}';
    }

    public static class Builder {
        private String lastName; // обязательный
        private String firstName;
        private String middleName;
        private String company; // обязательный
        private String department;
        private String position;

        Builder(String lastName, String company) {
            if (lastName == null || lastName.isEmpty()) throw new IllegalArgumentException("lastName is null or empty");
            if (company == null || company.isEmpty()) throw new IllegalArgumentException("company is null or empty");

            this.lastName = lastName;
            this.company = company;
        }

        Builder firstName(String firstName) {
            this.firstName = firstName;
            return this; // fluent interface
        }

        Builder middleName(String middleName) {
            this.middleName = middleName;
            return this;
        }

        public Builder company(String company) {
            this.company = company;
            return this;
        }

        Builder department(String department) {
            this.department = department;
            return this;
        }

        Builder position(String position) {
            this.position = position;
            return this;
        }

        Employee build() {
            return new Employee(this);
            //          Можно и так:
            //          return new Employee(lastName, firstName, middleName, company, department, position);
        }
    }
}
