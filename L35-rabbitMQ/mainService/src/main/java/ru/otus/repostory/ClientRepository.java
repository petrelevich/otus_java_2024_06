package ru.otus.repostory;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.domain.Client;

public interface ClientRepository extends JpaRepository<Client, Long> {}
