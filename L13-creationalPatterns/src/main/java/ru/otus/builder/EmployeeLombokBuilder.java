package ru.otus.builder;

import lombok.Builder;
import lombok.NonNull;
import lombok.Value;

@Value
@Builder(toBuilder = true)
public class EmployeeLombokBuilder {
    @NonNull
    private String lastName;

    private String firstName;
    private String middleName;

    @NonNull
    private String company;

    private String department;
    private String position;
}
