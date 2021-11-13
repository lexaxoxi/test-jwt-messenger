package com.mosk.testjwtmessenger.service;

import com.mosk.testjwtmessenger.dto.ArchMessages;
import com.mosk.testjwtmessenger.dto.InputMessage;
import lombok.NonNull;

import java.security.Principal;

public interface MessagesService {
    void add(@NonNull InputMessage inputMessage);
    ArchMessages getLastMessages(@NonNull int quantityMessages, @NonNull Principal principal) throws Exception;


}
