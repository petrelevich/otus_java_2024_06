package ru.otus.solution1.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuppressWarnings("java:S125")
public class OtusStudent {
    @Id
    private long id;

    private String name;

    // private Avatar avatar;
    // private List<EMail> emails;
    // private List<Course> courses;
}
