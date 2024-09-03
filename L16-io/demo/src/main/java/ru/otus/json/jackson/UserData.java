package ru.otus.json.jackson;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type", defaultImpl = UserData.class)
@JsonSubTypes({
    @JsonSubTypes.Type(value = Phone.class, name = "phone"),
    @JsonSubTypes.Type(value = Address.class, name = "address"),
    @JsonSubTypes.Type(value = UserData.class, name = "default")
})
public abstract class UserData {}
