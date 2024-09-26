package ru.petrelevich.repository;

import java.util.Optional;
import org.redisson.api.RedissonClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ValueCacheRedis implements ValueCache {
    private static final Logger log = LoggerFactory.getLogger(ValueCacheRedis.class);
    private final RedissonClient redissonClient;

    public ValueCacheRedis(RedissonClient redissonClient) {
        this.redissonClient = redissonClient;
    }

    @Override
    public Optional<Long> get(long val) {
        var value = redissonClient.getAtomicLong(getKeyName(val));
        if (value.isExists()) {
            var valueFromRedis = value.get();
            log.info("get value from Redis:{}", valueFromRedis);
            return Optional.of(valueFromRedis);
        }
        log.info("no value from Redis");
        return Optional.empty();
    }

    @Override
    public void put(long val) {
        var value = redissonClient.getAtomicLong(getKeyName(val));
        if (!value.isExists()) {
            log.info("put value to Redis:{}", val);
            value.set(val);
            var topic = redissonClient.getTopic("value");
            var clientsReceivedMessage = topic.publish(val);
            log.info("the number of clients that received the message:{}", clientsReceivedMessage);
        }
    }

    private String getKeyName(long val) {
        return String.valueOf(val);
    }
}
