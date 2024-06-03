package br.com.kyw.project_kyw.application.services;

import br.com.kyw.project_kyw.adapters.dtos.response.MessageResponse;
import br.com.kyw.project_kyw.application.acess.AccessProjectLevel;
import br.com.kyw.project_kyw.application.exceptions.AuthorizationException;
import br.com.kyw.project_kyw.application.repositories.MessageRepository;
import br.com.kyw.project_kyw.application.services.utils.Mapper;
import br.com.kyw.project_kyw.core.entities.Message;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class MessageService {

    private final MessageRepository messageRepository;

    private final AccessProjectLevel accessProjectLevel;

    private final Mapper mapper;

    public MessageService(MessageRepository messageRepository, AccessProjectLevel accessProjectLevel, Mapper mapper) {
        this.messageRepository = messageRepository;
        this.accessProjectLevel = accessProjectLevel;
        this.mapper = mapper;
    }

    @Transactional(readOnly = true)
    public Page<MessageResponse> getMessagesByProjectSentInDesc(Pageable pageable, UUID projectId){
        if(accessProjectLevel.canAcessProject(projectId)) {
            var messagesProject = messageRepository.findAllByProject_Id(pageable, projectId);
            return messagesProject.map(mapper::entityForDTOMessage);
        }
        throw new AuthorizationException("Você não tem autorização para acessar as mensagens desse projeto");
    }

    @Transactional(readOnly = true)
    public Page<Message> getMessagesByProjectSentInAsc(Pageable pageable){
        return messageRepository.findAllByOrderBySentInAsc(pageable);
    }
}
