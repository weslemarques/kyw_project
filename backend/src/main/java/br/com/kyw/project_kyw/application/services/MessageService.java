package br.com.kyw.project_kyw.application.services;

import br.com.kyw.project_kyw.adapters.dtos.response.MessageResponse;
import br.com.kyw.project_kyw.application.acess.AccessProjectLevel;
import br.com.kyw.project_kyw.application.exceptions.AuthorizationException;
import br.com.kyw.project_kyw.application.repositories.MessageRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class MessageService {

    private final MessageRepository messageRepository;

    private final AccessProjectLevel accessProjectLevel;

    private final ModelMapper mapper;

    public MessageService(MessageRepository messageRepository, AccessProjectLevel accessProjectLevel, ModelMapper mapper) {
        this.messageRepository = messageRepository;
        this.accessProjectLevel = accessProjectLevel;
        this.mapper = mapper;
    }

    @Transactional(readOnly = true)
    public List<MessageResponse> getMessagesByProjectSentIn(UUID projectId, int limit,int page ,String sort){
        if(accessProjectLevel.canAcessProject(projectId)) {
            PageRequest pageRequest = PageRequest.of(page, limit);
                if (sort.equals("desc")) {
                    var messages = messageRepository.findAllMessagesOrderBySentInDesc(projectId,pageRequest);
                    return messages.stream().map(message -> mapper.map(message, MessageResponse.class)).toList();
                }
                var messages = messageRepository.findAllMessagesOrderBySentInAsc(projectId,pageRequest);
                return messages.stream().map(message -> mapper.map(message, MessageResponse.class)).toList();
        }
        throw new AuthorizationException("Você não tem autorização para acessar as mensagens desse projeto");
    }

}
