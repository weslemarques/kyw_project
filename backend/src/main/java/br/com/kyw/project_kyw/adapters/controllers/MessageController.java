package br.com.kyw.project_kyw.adapters.controllers;

import br.com.kyw.project_kyw.adapters.dtos.response.MessageResponse;
import br.com.kyw.project_kyw.application.services.MessageService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/messages")
@SecurityRequirement(name = "Bearer ")
public class MessageController {

    private final MessageService messageService;
    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }
    @GetMapping("/project/{projectId}")
    public ResponseEntity<Page<MessageResponse>> getMessagesDesc(Pageable pageable, @PathVariable UUID projectId){
        return ResponseEntity.ok(messageService.getMessagesByProjectSentInDesc(pageable, projectId));
    }
}
