package br.com.kyw.project_kyw.adapters.controllers;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ChatController {

    @GetMapping(value = "/chat", produces = MediaType.TEXT_HTML_VALUE)
    public String getChat(){
        return "index";
    }
}
