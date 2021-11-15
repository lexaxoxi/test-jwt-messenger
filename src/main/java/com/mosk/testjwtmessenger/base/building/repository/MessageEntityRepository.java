package com.mosk.testjwtmessenger.base.building.repository;

import com.mosk.testjwtmessenger.base.building.entity.MessageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageEntityRepository extends JpaRepository<MessageEntity, Long> {
}
