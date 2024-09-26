package ru.petrelevich.transformer;

public class TransformException extends RuntimeException {
    public TransformException(Exception ex) {
        super(ex);
    }
}
