package ru.otus.tableineritance.model;

import jakarta.persistence.*;

@Entity
@DiscriminatorColumn(name = "discriminator")
@DiscriminatorValue("Root")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
// @Inheritance(strategy = InheritanceType.JOINED)
// @Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(name = "A")
public class A {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    // Не получится использовать при InheritanceType.TABLE_PER_CLASS
    // @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected long id;

    protected String fieldA;

    public A() {}

    public A(long id, String fieldA) {
        this.id = id;
        this.fieldA = fieldA;
    }

    @Override
    public String toString() {
        return "A{" + "id=" + id + ", fieldA='" + fieldA + '\'' + '}';
    }
}
