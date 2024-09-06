package ru.otus.nio;

import java.nio.CharBuffer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BufferExample {
    private static final Logger logger = LoggerFactory.getLogger(BufferExample.class);

    public static void main(String[] args) {
        new BufferExample().go();
    }

    private void go() {
        var buffer = CharBuffer.allocate(10);
        logger.info("capacity:{} limit:{} position:{}", buffer.capacity(), buffer.limit(), buffer.position());

        var text = "testText".toCharArray();
        for (var idx = 0; idx < text.length; idx += 2) {
            buffer.put(text, idx, 2);
            logger.info(
                    "idx:{} capacity:{} limit:{}} position:{}",
                    idx,
                    buffer.capacity(),
                    buffer.limit(),
                    buffer.position());
        }

        buffer.flip();

        logger.info("-----");
        for (var idx = 0; idx < buffer.limit(); idx++) {
            logger.info(
                    "idx:{} char:{} capacity:{} limit:{} position:{}",
                    idx,
                    buffer.get(),
                    buffer.capacity(),
                    buffer.limit(),
                    buffer.position());
        }
    }
}
