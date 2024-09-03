package ru.otus.io;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SuppressWarnings({"java:S125"})
public class DemoIO {
    private static final Logger logger = LoggerFactory.getLogger(DemoIO.class);

    public static void main(String[] args) throws Exception {
        logger.atInfo()
                .setMessage("current dir: {}")
                .addArgument(() -> System.getProperty("user.dir"))
                .log();
        var demoIO = new DemoIO();
        demoIO.zipFile("textFile.txt");
        //   demoIO.writeObject("person.bin");
        // demoIO.readObject("person.bin");
        //  demoIO.writeTextFile("textFile.txt");
        //  demoIO.readTextFile("textFile.txt");

    }

    // Обратите внимание на цепочку декораторов
    public void zipFile(String textFile) throws IOException {
        try (var bufferedInputStream = new BufferedInputStream(new FileInputStream(textFile));
                var zipOut =
                        new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(textFile + "_copy.zip")))) {

            var zipEntry = new ZipEntry(textFile);
            zipOut.putNextEntry(zipEntry);
            var buffer = new byte[2];
            int size;
            while ((size = bufferedInputStream.read(buffer, 0, buffer.length)) > 0) {
                zipOut.write(buffer, 0, size);
            }
            zipOut.closeEntry();
        }
    }

    public void writeObject(String personFile) throws IOException {
        try (var objectOutputStream = new ObjectOutputStream(new FileOutputStream(personFile))) {

            var person = new Person(92, "SerialPerson", "hidden");
            logger.info("serializing:{}", person);
            objectOutputStream.writeObject(person);
        }
    }

    public void readObject(String personFile) throws IOException, ClassNotFoundException {
        try (var objectInputStream = new ObjectInputStream(new FileInputStream(personFile))) {

            var person = (Person) objectInputStream.readObject();
            logger.info("read object:{}", person);
        }
    }

    public void writeTextFile(String textFile) throws IOException {
        var line1 = "Hello Java, str1";
        var line2 = "Hello Java, str2";
        try (var bufferedWriter = new BufferedWriter(new FileWriter(textFile))) {
            bufferedWriter.write(line1);
            bufferedWriter.newLine();
            bufferedWriter.write(line2);
        }
    }

    public void readTextFile(String textFile) throws IOException {
        try (var bufferedReader = new BufferedReader(new FileReader(textFile))) {
            logger.info("text from the file:");
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                logger.info("{}", line);
            }
        }
    }
}
