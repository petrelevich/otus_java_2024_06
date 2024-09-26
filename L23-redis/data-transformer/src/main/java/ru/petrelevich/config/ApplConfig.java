package ru.petrelevich.config;

import java.net.http.HttpClient;
import java.time.Duration;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.petrelevich.repository.ValueCache;
import ru.petrelevich.repository.ValueCacheRedis;
import ru.petrelevich.transformer.IntTransformer;

@Configuration
public class ApplConfig {
    private static final Logger log = LoggerFactory.getLogger(ApplConfig.class);

    @Bean
    public HttpClient httpClient() {
        return HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_1_1)
                .followRedirects(HttpClient.Redirect.NORMAL)
                .connectTimeout(Duration.ofSeconds(20))
                .build();
    }

    @Bean(destroyMethod = "shutdown")
    public RedissonClient redissonClient(
            @Value("${redis.url}") String url,
            @Value("${redis.password}") String password,
            @Value("${client-name}") String clientName) {
        log.info("redis.url:{}, clientName:{}", url, clientName);
        var config = new Config();
        config.useSingleServer()
                .setAddress(String.format("redis://%s", url))
                .setPassword(password)
                .setClientName(clientName)
                .setConnectionMinimumIdleSize(1)
                .setConnectionPoolSize(1);
        return Redisson.create(config);
    }

    @Bean
    public ValueCache valueCache(RedissonClient redissonClient) {
        return new ValueCacheRedis(redissonClient);
    }

    @Bean
    public IntTransformer intTransformer(
            @Value("${data-source.url}") String url, HttpClient httpClient, ValueCache valueCache) {
        return new IntTransformer(url, httpClient, valueCache);
    }
}
