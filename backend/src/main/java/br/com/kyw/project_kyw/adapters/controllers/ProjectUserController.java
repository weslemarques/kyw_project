package br.com.kyw.project_kyw.adapters.controllers;

import br.com.kyw.project_kyw.application.services.ProjectUserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/members")
@SecurityRequirement(name = "Bearer ")
public class ProjectUserController {
    private final ProjectUserService projectUserService;


    public ProjectUserController(ProjectUserService projectUserService) {
        this.projectUserService = projectUserService;
    }

    @PostMapping("/add/{projectId}")
    public ResponseEntity<Void> addUserToTheProjectByEmail(@PathVariable UUID projectId, String email){
        projectUserService.addUserToTheProject(projectId, email);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/add/{userId}/{projectId}")
    public ResponseEntity<Void> addMemberById(@PathVariable UUID userId, @PathVariable UUID projectId){
        projectUserService.addMemberToProjectById(userId,projectId);
        return ResponseEntity.noContent().build();
    }




}
