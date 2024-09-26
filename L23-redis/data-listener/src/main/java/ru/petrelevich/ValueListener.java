package ru.petrelevich;

import org.redisson.api.RedissonClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;

public class ValueListener implements CommandLineRunner {
    private static final Logger logger = LoggerFactory.getLogger(ValueListener.class);
    private final RedissonClient redissonClient;

    public ValueListener(RedissonClient redissonClient) {
        this.redissonClient = redissonClient;
    }

    public void listen() {
        var topic = redissonClient.getTopic("value");
        topic.addListener(Long.class, (channel, value) -> logger.info("new value:{}", value));
        logger.info("listener started");
    }

    @Override
    public void run(String... args) throws Exception {
        this.listen();
    }
}
