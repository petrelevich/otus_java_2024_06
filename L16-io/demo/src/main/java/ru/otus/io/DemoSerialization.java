package ru.otus.io;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DemoSerialization {
    private static final Logger logger = LoggerFactory.getLogger(DemoSerialization.class);

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        deserialize(serialize());
    }

    private static byte[] serialize() throws IOException {
        try (var byteArrayOutputStream = new ByteArrayOutputStream();
                var objectOutputStream = new ObjectOutputStream(byteArrayOutputStream)) {
            var person = new Person(12, "SerialPersonForArray", "value");
            logger.info("Serializing:{}", person);
            objectOutputStream.writeObject(person);
            return byteArrayOutputStream.toByteArray();
        }
    }

    private static void deserialize(byte[] data) throws IOException, ClassNotFoundException {
        try (var objectInputStream = new ObjectInputStream(new ByteArrayInputStream(data))) {
            var person = (Person) objectInputStream.readObject();
            logger.info("deSerialized person:{}", person);
        }
    }
}
