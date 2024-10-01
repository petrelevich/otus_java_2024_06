package ru.otus.dao;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import ru.otus.model.User;

public class InMemoryUserDao implements UserDao {

    public static final String DEFAULT_PASSWORD = "11111";
    private final Random random = new Random();
    private final Map<Long, User> users;

    public InMemoryUserDao() {
        users = new HashMap<>();
        users.put(1L, new User(1L, "Крис Гир", "user1", DEFAULT_PASSWORD));
        users.put(2L, new User(2L, "Ая Кэш", "user2", DEFAULT_PASSWORD));
        users.put(3L, new User(3L, "Десмин Боргес", "user3", DEFAULT_PASSWORD));
        users.put(4L, new User(4L, "Кетер Донохью", "user4", DEFAULT_PASSWORD));
        users.put(5L, new User(5L, "Стивен Шнайдер", "user5", DEFAULT_PASSWORD));
        users.put(6L, new User(6L, "Джанет Вэрни", "user6", DEFAULT_PASSWORD));
        users.put(7L, new User(7L, "Брэндон Смит", "user7", DEFAULT_PASSWORD));
    }

    @Override
    public Optional<User> findById(long id) {
        return Optional.ofNullable(users.get(id));
    }

    @Override
    public Optional<User> findRandomUser() {

        return users.values().stream().skip(random.nextInt(users.size() - 1)).findFirst();
    }

    @Override
    public Optional<User> findByLogin(String login) {
        return users.values().stream().filter(v -> v.getLogin().equals(login)).findFirst();
    }
}
