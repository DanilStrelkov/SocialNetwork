package ru.skillbox.zerone.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.skillbox.zerone.backend.model.entity.Message;

public interface MessageRepository extends JpaRepository<Message, Long> {
}
