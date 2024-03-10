package br.com.kyw.project_kyw;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Profile;

@SpringBootApplication
@Profile("dev")
public class ProjectKywApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProjectKywApplication.class, args);
    }

}
