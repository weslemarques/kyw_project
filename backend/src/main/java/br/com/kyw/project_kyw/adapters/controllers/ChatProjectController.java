package br.com.kyw.project_kyw.adapters.controllers;

import br.com.kyw.project_kyw.application.exceptions.ResourceNotFound;
import br.com.kyw.project_kyw.application.repositories.ProjectRepository;
import br.com.kyw.project_kyw.core.entities.Message;
import br.com.kyw.project_kyw.core.entities.Project;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
public class ChatProjectController {

    private final ProjectRepository projectRepository;

    public ChatProjectController(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }
    @MessageMapping("/{projectId}")
    @SendTo("chat/project/{projectId}")
    public Message send(@DestinationVariable UUID projectId, Message message) {
        Project project = projectRepository.findById (projectId)
                .orElseThrow(() -> new ResourceNotFound("Group not found"));
            project.getMessages().add(message);
            projectRepository.save(project);
        return message;
    }
}