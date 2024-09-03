package ru.otus.json.jackson;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JacksonDemo {
    private static final Logger logger = LoggerFactory.getLogger(JacksonDemo.class);
    private final ObjectMapper mapper;
    private final User user = new User(32, "Jack", null, LocalDateTime.now(), "someData");

    public static void main(String[] args) throws IOException {

        var demo = new JacksonDemo();
        var fileName = "jack.json";
        demo.save(fileName);
        demo.loadUser(fileName);

        var userAsString = demo.writeAsString();
        logger.info("userAsString:{}", userAsString);

        var userFromString = demo.readFromString(userAsString);
        logger.info("user from string:{}", userFromString);

        var propertyValue = demo.readPropValue(userAsString, "nameForSerialization");
        logger.info("propertyValue:{}", propertyValue);

        demo.innerObject();
    }

    public JacksonDemo() {
        this.mapper = JsonMapper.builder().build();
        mapper.registerModule(new JavaTimeModule());
    }

    private void save(String fileName) throws IOException {
        var file = new File(fileName);
        mapper.writeValue(file, user);
        logger.info("user saved to the file:{}", file.getAbsolutePath());
    }

    private void loadUser(String fileName) throws IOException {
        var file = new File(fileName);
        var userLoaded = mapper.readValue(file, User.class);
        logger.info("user loaded from the file:{}, user:{}", file.getAbsolutePath(), userLoaded);
    }

    private String writeAsString() throws JsonProcessingException {
        return mapper.writeValueAsString(user);
    }

    private User readFromString(String userAsString) throws JsonProcessingException {
        return mapper.readValue(userAsString, User.class);
    }

    private String readPropValue(String userAsString, String nameForSerialization) throws JsonProcessingException {
        JsonNode node = mapper.readTree(userAsString);
        JsonNode jsonProp = node.get(nameForSerialization);
        return jsonProp.textValue();
    }

    private void innerObject() throws JsonProcessingException {
        var userAddr = new User(16, "Ivan", new Address("Magnitogorsk", "Sovetskaya"), LocalDateTime.now(), "data2");
        var userAddrAsString = mapper.writeValueAsString(userAddr);
        logger.info("userAddrAsString:{}", userAddrAsString);

        var userAddrFromString = mapper.readValue(userAddrAsString, User.class);
        logger.info("userAddrFromString:{}", userAddrFromString);

        var userPhone = new User(16, "Ivan", new Phone("+7-915-122-16"), LocalDateTime.now(), "data2");
        var userPhoneAsString = mapper.writeValueAsString(userPhone);
        logger.info("userPhoneAsString:{}", userPhoneAsString);

        var userPhoneFromString = mapper.readValue(userPhoneAsString, User.class);
        logger.info("userPhoneFromString:{}", userPhoneFromString);
    }
}
