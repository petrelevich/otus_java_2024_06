package ru.otus.services;

import java.util.List;
import ru.otus.domain.Client;

public interface ClientService {
    List<Client> findAll();

    Client save(Client client);

    Client saveAndSendForApprove(Client client);

    Client saveAndSendForApproveAndWait(Client client);
}
