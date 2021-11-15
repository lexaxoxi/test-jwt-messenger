package com.mosk.testjwtmessenger.base.building.controller;

import com.mosk.testjwtmessenger.base.building.converter.MessageEntityConverter;
import com.mosk.testjwtmessenger.base.building.dto.request.MessageRequest;
import com.mosk.testjwtmessenger.base.building.dto.response.MessageResponse;
import com.mosk.testjwtmessenger.base.building.entity.MessageEntity;
import com.mosk.testjwtmessenger.base.building.entity.UserEntity;
import com.mosk.testjwtmessenger.base.building.service.MessageService;
import com.mosk.testjwtmessenger.base.building.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class MessageController {

    private final UserService userService;
    private final MessageService messageService;
    private final MessageEntityConverter converter;

    @Autowired
    public MessageController(UserService userService, MessageService messageService, MessageEntityConverter converter) {
        this.userService = userService;
        this.messageService = messageService;
        this.converter = converter;
    }

    //добавление сообщений в БД
    @PostMapping("/message")
    @ResponseBody
    public List<MessageResponse> addMessage(@RequestBody @Valid MessageRequest messageRequest) {
        List<MessageResponse> result = new ArrayList<>();
        UserEntity user = userService.findByLogin(messageRequest.getLogin());
        MessageEntity messageEntity = messageService.saveMessage(user, messageRequest.getMessage());
        result.add(converter.convertToMessageResponse(messageEntity));
        return result;
    }

    //получение истории сообщений
    @GetMapping("/message/history")
    @ResponseBody
    public List<MessageResponse> getMessage(@RequestBody @Valid MessageRequest messageRequest) {
        List<MessageResponse> result = new ArrayList<>();
        if (messageRequest.getMessage().startsWith("history ")) {
            int limit = Integer.parseInt(messageRequest.getMessage().replaceAll("[^0-9]+", ""));
            return messageService.getMessageHistory(limit)
                    .stream()
                    .map(converter::convertToMessageResponse)
                    .collect(Collectors.toList());
        }
        return result;

    }
}

