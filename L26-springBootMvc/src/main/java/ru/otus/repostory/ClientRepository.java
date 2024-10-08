package ru.otus.repostory;

import java.util.List;
import ru.otus.domain.Client;

public interface ClientRepository {

    List<Client> findAll();

    Client save(Client client);

    Client findById(long id);

    Client findByName(String name);
}
