package com.mosk.testjwtmessenger.controller.interfaces;

import com.mosk.testjwtmessenger.dto.ArchMessages;
import com.mosk.testjwtmessenger.dto.InputMessage;
import lombok.NonNull;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;


public interface MessageControllerInt {


    void addMessage(@NonNull @RequestBody InputMessage inputMessage);

    ArchMessages getLastMessages(@NonNull @RequestParam(name = "quantity") int quantityMessages, @NonNull Principal principal) throws Exception;
}
