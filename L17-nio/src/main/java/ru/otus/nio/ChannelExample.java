package ru.otus.nio;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Paths;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ChannelExample {
    private static final Logger logger = LoggerFactory.getLogger(ChannelExample.class);

    public static void main(String[] args) throws IOException, URISyntaxException {
        new ChannelExample().go();
    }

    private void go() throws IOException, URISyntaxException {
        var path = Paths.get(ClassLoader.getSystemResource("share.xml").toURI());
        try (var fileChannel = FileChannel.open(path)) {
            var buffer = ByteBuffer.allocate(10);

            int bytesCount;
            var sb = new StringBuilder();
            do {
                bytesCount = fileChannel.read(buffer);
                buffer.flip();
                while (buffer.hasRemaining()) {
                    sb.append((char) buffer.get());
                }
                buffer.flip();
            } while (bytesCount > 0);

            logger.info("result:{}", sb);
        }
    }
}
