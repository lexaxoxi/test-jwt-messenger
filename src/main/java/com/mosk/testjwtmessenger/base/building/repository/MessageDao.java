package com.mosk.testjwtmessenger.base.building.repository;

import com.mosk.testjwtmessenger.base.building.entity.MessageEntity;

import java.util.List;

public interface MessageDao {

    List<MessageEntity> getMessageHistory(int limit);
}
