package ru.otus.protobuf.service;

import java.util.List;
import ru.otus.protobuf.model.User;

public interface RealDBService {
    User saveUser(String firstName, String lastName);

    List<User> findAllUsers();
}
