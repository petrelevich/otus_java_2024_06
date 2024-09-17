package ru.otus.exercise.model;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuppressWarnings("java:S125")
public class OtusStudent {
    private long id;
    private String name;

    // private Avatar avatar;
    // private List<EMail> emails;
    // private List<Course> courses;
}
