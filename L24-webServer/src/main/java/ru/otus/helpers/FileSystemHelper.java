package ru.otus.helpers;

import java.io.File;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

@SuppressWarnings({"squid:S112"})
public final class FileSystemHelper {

    private FileSystemHelper() {}

    public static String localFileNameOrResourceNameToFullPath(String fileOrResourceName) {
        String path = null;
        File file = new File(String.format("./%s", fileOrResourceName));
        if (file.exists()) {
            path = URLDecoder.decode(file.toURI().getPath(), StandardCharsets.UTF_8);
        }

        if (path == null) {
            path = Optional.ofNullable(FileSystemHelper.class.getClassLoader().getResource(fileOrResourceName))
                    .orElseThrow(() -> new RuntimeException(String.format("File \"%s\" not found", fileOrResourceName)))
                    .toExternalForm();
        }
        return path;
    }
}
