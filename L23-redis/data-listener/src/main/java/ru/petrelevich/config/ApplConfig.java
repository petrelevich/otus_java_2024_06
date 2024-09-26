package ru.petrelevich.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.petrelevich.ValueListener;

@Configuration
public class ApplConfig {
    private static final Logger log = LoggerFactory.getLogger(ApplConfig.class);

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
    public ValueListener valueListener(RedissonClient redissonClient) {
        return new ValueListener(redissonClient);
    }
}
