package ru.otus.json.jackson;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@JsonIgnoreProperties({"transientProperty"})
public class User {
    public static final String JSON_DATE_TIME_FORMAT = "yyyy.MM.dd HH:mm:ss";
    private int age;

    @JsonProperty("nameForSerialization")
    private String name;

    @JsonProperty("userData")
    private UserData userData;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = JSON_DATE_TIME_FORMAT)
    private LocalDateTime date;

    private String transientProperty = "lostData";
}
