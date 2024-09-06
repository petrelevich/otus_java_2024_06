package ru.otus.nio.nio2;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.NoSuchElementException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PathExample {
    private static final Logger logger = LoggerFactory.getLogger(PathExample.class);

    public static void main(String[] args) throws IOException, URISyntaxException {
        new PathExample().go();
    }

    private void go() throws IOException, URISyntaxException {
        var fileUrl = ClassLoader.getSystemResource("share.xml");
        if (fileUrl == null) {
            throw new NoSuchElementException("Resource file share.xml not found");
        }
        Path shareXml = Paths.get(fileUrl.toURI());
        logger.info("FileName:{}", shareXml.getFileName());
        logger.info("FileSystem:{}", shareXml.getFileSystem());
        logger.info("Parent:{}", shareXml.getParent());
        logger.info("isAbsolute:{}", shareXml.isAbsolute());
        logger.info("realPath:{}", shareXml.toRealPath());

        var notExists = Paths.get("NotExists.xml");
        try {
            logger.info("realPath: {}", notExists.toRealPath());
        } catch (IOException ex) {
            logger.error(ex.getMessage(), ex);
        }
    }
}
