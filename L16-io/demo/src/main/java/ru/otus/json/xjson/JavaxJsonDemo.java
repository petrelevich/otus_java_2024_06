package ru.otus.json.xjson;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonNumber;
import jakarta.json.JsonObject;
import jakarta.json.JsonString;
import jakarta.json.JsonStructure;
import jakarta.json.JsonValue;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JavaxJsonDemo {
    private static final Logger logger = LoggerFactory.getLogger(JavaxJsonDemo.class);

    public static void main(String[] args) {
        navigateTree(create());
        readFromFile();
    }

    private static JsonObject create() {
        var jsonObject = Json.createObjectBuilder()
                .add("firstName", "Duke")
                .add("age", 28)
                .add("streetAddress", "100 Internet Dr")
                .add(
                        "phoneNumbers",
                        Json.createArrayBuilder()
                                .add(Json.createObjectBuilder()
                                        .add("type", "home")
                                        .add("number", "222-222-2222")))
                .build();

        logger.info("jsonObject:{}", jsonObject);
        return jsonObject;
    }

    private static void navigateTree(JsonValue tree) {
        switch (tree.getValueType()) {
            case OBJECT -> {
                logger.info("OBJECT");
                var jsonObject = (JsonObject) tree;
                for (Map.Entry<String, JsonValue> entry : jsonObject.entrySet()) {
                    navigateTree(jsonObject.get(entry.getKey()));
                }
            }
            case ARRAY -> {
                logger.info("ARRAY");
                JsonArray array = (JsonArray) tree;
                for (JsonValue val : array) {
                    navigateTree(val);
                }
            }
            case STRING -> {
                JsonString st = (JsonString) tree;
                logger.info("STRING {}", st.getString());
            }
            case NUMBER -> {
                JsonNumber num = (JsonNumber) tree;
                logger.info("NUMBER {}", num);
            }
            case TRUE, FALSE, NULL -> logger.atInfo()
                    .setMessage("{}")
                    .addArgument(tree::getValueType)
                    .log();
            default -> throw new IllegalStateException("Unexpected value: " + tree.getValueType());
        }
    }

    private static void readFromFile() {
        try (var jsonReader =
                Json.createReader(JavaxJsonDemo.class.getClassLoader().getResourceAsStream("jsondata.txt"))) {
            JsonStructure jsonFromTheFile = jsonReader.read();
            logger.info("\n json from the file:");
            logger.info("{}", jsonFromTheFile);
            logger.info("property:{}", jsonFromTheFile.getValue("/firstName"));
        }
    }
}
