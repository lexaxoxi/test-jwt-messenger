package com.mosk.testjwtmessenger.base.building.converter;

import com.mosk.testjwtmessenger.base.building.dto.response.MessageResponse;
import com.mosk.testjwtmessenger.base.building.entity.MessageEntity;
import org.springframework.stereotype.Component;

@Component
public class MessageEntityConverter {

    public MessageResponse convertToMessageResponse(MessageEntity messageEntity) {
        return new MessageResponse(messageEntity.getId_message(), messageEntity.getText_message(),
                messageEntity.getSpeaker().getLogin());
    }
}
