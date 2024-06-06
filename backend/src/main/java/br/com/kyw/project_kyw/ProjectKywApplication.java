package br.com.kyw.project_kyw;

import br.com.kyw.project_kyw.application.services.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.UUID;

@SpringBootApplication
public class ProjectKywApplication {
    public static void main(String[] args) {
        SpringApplication.run(ProjectKywApplication.class, args);
    }



}
