package com.mosk.testjwtmessenger.controller;

import com.mosk.testjwtmessenger.controller.interfaces.MessageControllerInt;
import com.mosk.testjwtmessenger.dto.ArchMessages;
import com.mosk.testjwtmessenger.dto.InputMessage;
import com.mosk.testjwtmessenger.service.MessagesService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.security.Principal;

@RestController
@RequestMapping("/messages")
@PreAuthorize("isAuthenticated()")
@RequiredArgsConstructor
public class MessageController implements MessageControllerInt {
    private final MessagesService messagesService;

    @PostMapping("add")
    @Override
    public void addMessage(@NonNull InputMessage inputMessage) {
        messagesService.add(inputMessage);


    }

    @GetMapping("last")
    @Override
    public ArchMessages getLastMessages(@NonNull int quantityMessages, @NonNull Principal principal) throws Exception {
        return messagesService.getLastMessages(quantityMessages, principal);
    }

}
