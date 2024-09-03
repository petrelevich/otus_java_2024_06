package ru.otus.protobuf;

import com.google.protobuf.util.JsonFormat;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Demo {
    private static final Logger logger = LoggerFactory.getLogger(Demo.class);

    public static void main(String[] args) throws IOException {
        var fileName = "protoTest.bin";

        var helloMessage = HelloWorld.HelloMessage.newBuilder()
                .setMessage("Hello, World, from Protobuf")
                .addArrayIntData(10)
                .addArrayIntData(20)
                .addArrayIntData(30)
                .build();

        var messageJson = JsonFormat.printer().print(helloMessage);
        logger.info("messageJson:{}", messageJson);

        var builder = HelloWorld.HelloMessage.newBuilder();
        JsonFormat.parser().merge(messageJson, builder);
        var messageFromJson = builder.build();
        logger.info(
                "messageFromJson:{}, array:{}", messageFromJson.getMessage(), messageFromJson.getArrayIntDataList());

        try (var fileOutputStream = new FileOutputStream(fileName)) {
            helloMessage.writeTo(fileOutputStream);
        }

        try (var fileInputStream = new FileInputStream(fileName)) {
            var messageIn = HelloWorld.HelloMessage.parseFrom(fileInputStream);
            logger.info("messageIn:{}, array:{}", messageIn.getMessage(), messageIn.getArrayIntDataList());
        }
    }
}
