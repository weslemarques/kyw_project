package br.com.kyw.project_kyw.adapters.controllers;

import br.com.kyw.project_kyw.adapters.dtos.response.MessageResponse;
import br.com.kyw.project_kyw.application.services.MessageService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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
    public ResponseEntity<List<MessageResponse>> getMessagesDesc(@PathVariable UUID projectId, @RequestParam(defaultValue = "10") int limit,
                                                                 @RequestParam(defaultValue = "0") int page, @RequestParam String sort){
        return ResponseEntity.ok(messageService.getMessagesByProjectSentIn(projectId, limit,page, sort));
    }


}
