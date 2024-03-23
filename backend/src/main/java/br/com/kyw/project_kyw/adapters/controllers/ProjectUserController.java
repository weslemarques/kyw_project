package br.com.kyw.project_kyw.adapters.controllers;

import br.com.kyw.project_kyw.application.services.ProjectUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/projects/{projectId}")
public class ProjectUserController {
    private final ProjectUserService projectUserService;

    public ProjectUserController(ProjectUserService projectUserService) {
        this.projectUserService = projectUserService;
    }

    @PostMapping("/members")
    public ResponseEntity<Void> addUserToTheProjectByEmail(@PathVariable UUID projectId, String email){
        projectUserService.addUserToTheProject(projectId, email);
        return ResponseEntity.ok().build();
    }
}
