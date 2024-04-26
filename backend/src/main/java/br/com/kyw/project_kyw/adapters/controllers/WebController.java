package br.com.kyw.project_kyw.adapters.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {
    @GetMapping("/chat")
    public String getChat(){
        return "index.html";
    }
}
