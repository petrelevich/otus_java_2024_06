package ru.otus.solution2.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "students")
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuppressWarnings("java:S125")
public class OtusStudent {
    @Id
    private long id;

    @Column(name = "student_name")
    private String name;

    // private Avatar avatar;
    // private List<EMail> emails;
    // private List<Course> courses;
}
