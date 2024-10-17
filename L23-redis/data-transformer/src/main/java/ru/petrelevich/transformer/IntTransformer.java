package ru.petrelevich.transformer;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.petrelevich.repository.ValueCache;

public class IntTransformer {
    private static final Logger log = LoggerFactory.getLogger(IntTransformer.class);
    private final HttpClient httpClient;
    private final String url;
    private final ValueCache valueCache;

    public IntTransformer(String url, HttpClient httpClient, ValueCache valueCache) {
        this.url = url;
        this.httpClient = httpClient;
        this.valueCache = valueCache;
    }

    public long transform(long value) {
        var valueFromCache = valueCache.get(value);
        long valueForTransform;
        if (valueFromCache.isPresent()) {
            valueForTransform = valueFromCache.get();
        } else {
            valueForTransform = getFromDataSource(value);
            valueCache.put(valueForTransform);
        }

        var result = valueForTransform * 10;
        log.info("value:{}, valueForTransform:{}, result:{}", value, valueForTransform, result);
        return result;
    }

    private long getFromDataSource(long value) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(String.format("%s/value?val=%d", url, value)))
                    .timeout(Duration.ofMinutes(1))
                    .header("Content-Type", "text/plain")
                    .build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            return Long.parseLong(response.body());
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
            throw new TransformException(ex);
        } catch (Exception ex) {
            throw new TransformException(ex);
        }
    }
}
