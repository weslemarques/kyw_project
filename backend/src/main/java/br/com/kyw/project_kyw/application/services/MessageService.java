package br.com.kyw.project_kyw.application.services;

import br.com.kyw.project_kyw.application.repositories.MessageRepository;
import br.com.kyw.project_kyw.core.entities.Message;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MessageService {

    private final MessageRepository messageRepository;

    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @Transactional(readOnly = true)
    public Page<Message> getMessagesByProjectSentInDesc(Pageable pageable){
        return messageRepository.findAllByOrderBySentInDesc(pageable);
    }

    @Transactional(readOnly = true)
    public Page<Message> getMessagesByProjectSentInAsc(Pageable pageable){
        return messageRepository.findAllByOrderBySentInAsc(pageable);
    }
}
