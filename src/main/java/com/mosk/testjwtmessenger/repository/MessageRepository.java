package com.mosk.testjwtmessenger.repository;

import com.mosk.testjwtmessenger.entity.MessageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<MessageEntity, Long> {
}