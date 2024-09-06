package ru.otus.nio.nio2;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FilesExample {
    private static final Logger logger = LoggerFactory.getLogger(FilesExample.class);

    public static void main(String[] args) throws IOException, URISyntaxException {
        var filesExample = new FilesExample();

        filesExample.exists();
        filesExample.copy();
        filesExample.read();
        filesExample.write();
        filesExample.walk();
    }

    private void exists() {
        var path = Paths.get("L17-nio/data.xml");
        var pathExists = Files.exists(path);
        logger.info("pathExists:{}", pathExists);

        var pathNE = Paths.get("L17-nio/NE.xml");
        boolean pathNotExists = Files.exists(pathNE);
        logger.info("\npathExists:{}", pathNotExists);
    }

    private void copy() throws IOException, URISyntaxException {
        Files.createDirectory(Paths.get("L17-nio/tmp"));

        // с файлами в ресурсах надо работать как с ресурсами
        var uri = ClassLoader.getSystemResource("share.xml").toURI();

        var source = Paths.get(uri);
        var destination = Paths.get("L17-nio/tmp/share.xml");

        Files.copy(source, destination);
    }

    private void read() throws IOException {
        var path = Paths.get("L17-nio/data.xml");
        var size = Files.size(path);
        logger.info("\nfile size: {}", size);

        logger.info("\ncontentAll:");

        try (var stream = Files.lines(path)) {
            stream.forEach(logger::info);
        }

        logger.info("\nfiltered:");

        try (var stream = Files.lines(path)) {
            stream.filter(line -> line.contains("share"))
                    .map(String::toUpperCase)
                    .forEach(logger::info);
        }
    }

    private void write() throws IOException {
        var testString = "Test-Test-Data-String";
        Files.write(Paths.get("L17-nio/tmp/newFile.txt"), testString.getBytes());
    }

    private void walk() {
        try (Stream<Path> paths = Files.walk(Paths.get("L17-nio"))) {
            var fileNames = paths.filter(Files::isRegularFile)
                    .filter(path -> path.getFileName().toString().contains(".java"))
                    .map(path -> {
                        try {
                            var content = Files.readString(path);
                            logger.info("fileName:{}, content:{}", path.getFileName(), content);
                        } catch (IOException e) {
                            logger.error("reading error, fileName:{}", path.getFileName(), e);
                        }
                        return path.getFileName().toString();
                    })
                    .toList();
            logger.info("file names:{}", fileNames);
        } catch (IOException e) {
            logger.error("error", e);
        }
    }
}
