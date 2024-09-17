package ru.otus.solution2.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "avatars")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Avatar {
    @Id
    private long id;

    @Column(name = "photo_url")
    private String photoUrl;
}
