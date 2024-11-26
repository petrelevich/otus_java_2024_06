package ru.otus.http.server;

public class ServerException extends RuntimeException {
    public ServerException(Throwable cause) {
        super(cause);
    }

    public ServerException(String msg) {
        super(msg);
    }
}
