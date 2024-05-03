package br.com.kyw.project_kyw.adapters.controllers;

import br.com.kyw.project_kyw.adapters.dtos.request.MessageRequestDTO;
import br.com.kyw.project_kyw.adapters.dtos.response.MessageResponse;
import br.com.kyw.project_kyw.application.exceptions.ResourceNotFound;
import br.com.kyw.project_kyw.application.repositories.MessageRepository;
import br.com.kyw.project_kyw.application.repositories.ProjectRepository;
import br.com.kyw.project_kyw.application.repositories.UserRepository;
import br.com.kyw.project_kyw.application.services.utils.Mapper;
import br.com.kyw.project_kyw.core.entities.Message;
import br.com.kyw.project_kyw.core.entities.Project;
import br.com.kyw.project_kyw.core.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@RestController
public class ChatProjectController {

    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;
    private final MessageRepository messageRepository;

    private final Mapper mapper;

    @Autowired
    public ChatProjectController(ProjectRepository projectRepository, UserRepository userRepository, MessageRepository messageRepository, Mapper mapper) {
        this.projectRepository = projectRepository;
        this.userRepository = userRepository;
        this.messageRepository = messageRepository;
        this.mapper = mapper;
    }
    @MessageMapping("/project/{projectId}")
    @SendTo("/project/{projectId}")
    public CompletableFuture<MessageResponse> send(@DestinationVariable UUID projectId, MessageRequestDTO messageDto) {
        System.out.println(messageDto + "" + projectId);
        return CompletableFuture.supplyAsync(() -> {
            Project project = projectRepository.findById(projectId)
                    .orElseThrow(() -> new ResourceNotFound("Project não encontrado"));
            User user = userRepository.findById(messageDto.sender())
                    .orElseThrow(() -> new ResourceNotFound("User não encontrado"));
            Message message = new Message();
            message.setProject(project);
            message.setSender(user);
            message.setContent(messageDto.content());
            message = messageRepository.save(message);
            return mapper.entityForDTOMessage(message);
        });
    }
}