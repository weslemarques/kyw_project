package br.com.kyw.project_kyw.adapters.controllers;

import br.com.kyw.project_kyw.application.services.MessageService;
import br.com.kyw.project_kyw.core.entities.Message;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/messages")
public class MessageController {


    private final MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping("/sent/desc")
    public ResponseEntity<Page<Message>> getMessagesDesc(Pageable pageable){
        return ResponseEntity.ok(messageService.getMessagesByProjectSentInDesc(pageable));
    }

    @GetMapping("/sent/asc")
    public ResponseEntity<Page<Message>> getMessagesAsc(Pageable pageable){
        return ResponseEntity.ok(messageService.getMessagesByProjectSentInAsc(pageable));
    }
}
