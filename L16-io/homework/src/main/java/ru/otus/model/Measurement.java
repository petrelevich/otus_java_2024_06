package ru.otus.model;

// Допустим, этот класс библиотечный, его нельзя менять
public record Measurement(String name, double value) {

    @Override
    public String toString() {
        return "Measurement{" + "name='" + name + '\'' + ", value=" + value + '}';
    }
}
