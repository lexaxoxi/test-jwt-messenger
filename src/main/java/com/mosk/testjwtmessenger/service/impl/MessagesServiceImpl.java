package com.mosk.testjwtmessenger.service.impl;

import com.mosk.testjwtmessenger.dto.ArchMessages;
import com.mosk.testjwtmessenger.dto.InputMessage;
import com.mosk.testjwtmessenger.dto.MessageDTO;
import com.mosk.testjwtmessenger.entity.MessageEntity;
import com.mosk.testjwtmessenger.entity.UserEntity;
import com.mosk.testjwtmessenger.repository.MessageRepository;
import com.mosk.testjwtmessenger.repository.UserEntityRepository;
import com.mosk.testjwtmessenger.service.MessagesService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityManager;
import java.security.Principal;
import java.util.List;

@Service
@PreAuthorize("isAuthenticated()")
@RequiredArgsConstructor
@Log4j2
public class MessagesServiceImpl implements MessagesService {
    private final MessageRepository messageRepository;
    private final EntityManager entityManager;
    private final UserEntityRepository userRepository;

    @Override
    public void add(@NonNull InputMessage inputMessage) {
        MessageEntity message = new MessageEntity();
        message.setText_message(inputMessage.message());
        messageRepository.save(message);
    }

    @Override
    public ArchMessages getLastMessages(@NonNull int quantityMessages, @NonNull Principal principal) {
        UserEntity user = userRepository.findByLogin(principal.getName());

        List<MessageEntity> messages = getMessages(user, 0, quantityMessages);

        if (messages.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Messages from this user is empty", principal.getName()));
        }
        List<MessageDTO> messageDTOS = (List<MessageDTO>) messages.stream()
                .map(message -> new MessageDTO(message.getText_message(), message.getLogin().getLogin()));
        return new ArchMessages(user.getLogin(), messageDTOS);
    }

    //         На стороне базы выбираем записи конкретного пользователя, сортируем и забираем n последних записей.
    private List<MessageEntity> getMessages(@NonNull UserEntity user, int offset, int quantity) {
        return entityManager
                .createQuery("SELECT m FROM MessageEntity m WHERE m.login = :login ORDER BY m.id_message ASC")
                .setParameter("login", user)
                .setFirstResult(offset)
                .setMaxResults(quantity)
                .getResultList();

    }
}
