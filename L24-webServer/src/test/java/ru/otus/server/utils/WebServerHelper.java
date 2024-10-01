package ru.otus.server.utils;

import java.util.Arrays;
import java.util.Optional;

public final class WebServerHelper {

    private WebServerHelper() {}

    public static String buildUrl(String host, String path, String... pathParams) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(host);
        stringBuilder.append(path);
        Optional.ofNullable(pathParams).ifPresent(paramsMap -> Arrays.stream(paramsMap)
                .forEach(p -> stringBuilder.append("/").append(p)));
        return stringBuilder.toString();
    }
}
