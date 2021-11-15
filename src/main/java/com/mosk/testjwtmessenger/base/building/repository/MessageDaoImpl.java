package com.mosk.testjwtmessenger.base.building.repository;

import com.mosk.testjwtmessenger.base.building.entity.MessageEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class MessageDaoImpl implements MessageDao {

    @PersistenceContext
    private final EntityManager entityManager;

    @Autowired
    public MessageDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public List<MessageEntity> getMessageHistory(int limit) {
        return entityManager.createNativeQuery("SELECT * FROM messages ORDER BY id_message DESC LIMIT :limit",
                MessageEntity.class).setParameter("limit", limit).getResultList();
    }
}
