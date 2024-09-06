package ru.otus.nio.examples;

public class AsyncReadException extends RuntimeException {
    public AsyncReadException(Throwable cause) {
        super(cause);
    }
}
