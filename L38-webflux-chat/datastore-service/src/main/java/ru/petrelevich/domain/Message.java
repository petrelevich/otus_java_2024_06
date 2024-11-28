package ru.petrelevich.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
import reactor.util.annotation.NonNull;

@Table("message")
public record Message(@Id Long id, @NonNull String roomId, @NonNull String msgText) {}
