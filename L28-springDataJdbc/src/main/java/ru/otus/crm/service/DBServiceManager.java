package ru.otus.crm.service;

import java.util.List;
import java.util.Optional;
import ru.otus.crm.model.Manager;

public interface DBServiceManager {

    Manager saveManager(Manager client);

    Optional<Manager> getManager(String no);

    List<Manager> findAll();
}
