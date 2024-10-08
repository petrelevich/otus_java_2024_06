package ru.otus.services;

import java.util.List;
import ru.otus.domain.Client;

public interface ClientService {
    List<Client> findAll();

    Client findById(long id);

    Client findByName(String name);

    Client findRandom();

    Client save(Client client);
}
