package com.mosk.testjwtmessenger.base.building.service;

import com.mosk.testjwtmessenger.base.building.entity.MessageEntity;
import com.mosk.testjwtmessenger.base.building.entity.UserEntity;
import com.mosk.testjwtmessenger.base.building.repository.MessageDao;
import com.mosk.testjwtmessenger.base.building.repository.MessageEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService {
    private final MessageEntityRepository messageRepo;
    private final MessageDao messageDao;


    @Autowired
    public MessageService(
            MessageEntityRepository messageRepo, MessageDao messageDao) {
        this.messageRepo = messageRepo;
        this.messageDao = messageDao;
    }

    public MessageEntity saveMessage(UserEntity speaker, String message) {
        MessageEntity messageEntity = new MessageEntity();
        messageEntity.setText_message(message);
        messageEntity.setSpeaker(speaker);
        return messageRepo.save(messageEntity);
    }

    public List<MessageEntity> getMessageHistory(int limit) {
        return messageDao.getMessageHistory(limit);
    }
}
