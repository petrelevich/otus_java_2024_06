package var;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;

@SuppressWarnings({"java:S106", "java:S108", "java:S125", "java:S1172", "java:S1481"})
public class VarIntro {
    // var field; ошибка компиляции

    public static void main(String[] args) throws IOException {
        new VarIntro().test(5);
    }

    // private var test(var val) throws IOException { Ошибка компиляции
    public String test(int val) throws IOException {
        var example = "ValueStr"; // String
        example = "newString";
        // example = 5; нельзя изменить тип
        // var error = null; ошибка, тип неопределен

        try (InputStream is = new FileInputStream("lines.txt")) {
            System.out.println(is);
        }

        // FileInputStream
        try (var is = new FileInputStream("lines.txt")) {
            System.out.println(is);
        }

        // List<String> list = Arrays.asList("1", "2", "3");
        var list = Arrays.asList("1", "2", "3");
        for (var str : list) {
            System.out.println(str);
        }

        // var nameList = new ArrayList<>(); тоже не понятен тип
        var stringList = new ArrayList<String>(); // а тут уже все ясно

        // Посмотрите на var в debug

        var testString = "StringVal";

        if (testString instanceof String) {
            System.out.println("testString is String");
        }

        return "";
    }
}
