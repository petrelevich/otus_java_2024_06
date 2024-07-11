package ru.otus.testing.example.services;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class StreamsIOService implements IOService {
    private final PrintStream out;
    private final Scanner sc;

    public StreamsIOService(PrintStream out, InputStream in) {
        this.out = out;
        this.sc = new Scanner(in);
    }

    @Override
    public void out(String message) {
        out.println(message);
    }

    @Override
    public String readString() {
        return sc.nextLine();
    }
}
