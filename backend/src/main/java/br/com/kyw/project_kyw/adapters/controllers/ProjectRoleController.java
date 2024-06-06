package br.com.kyw.project_kyw.adapters.controllers;

import br.com.kyw.project_kyw.adapters.dtos.response.ProjectRoleResponse;
import br.com.kyw.project_kyw.application.services.ProjectRoleService;
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
@RequestMapping("/projects/roles")
@SecurityRequirement(name = "Bearer ")
public class ProjectRoleController {

    private final ProjectRoleService projectRoleService;

    public ProjectRoleController(ProjectRoleService projectRoleService) {
        this.projectRoleService = projectRoleService;
    }
    @GetMapping("/{userId}")
    public ResponseEntity<Page<ProjectRoleResponse>> getByUserId(@PathVariable UUID userId, Pageable pageable){
       var pageProRole = projectRoleService.getProjectRoleByUser(pageable);
       return ResponseEntity.ok(pageProRole);
    }
    @GetMapping("/{projectId}")
    public ResponseEntity<String> getByProjectAndUserId(@PathVariable UUID projectId){
        var projectRole = projectRoleService.getProjectRoleByProjectAndUser(projectId);
        return ResponseEntity.ok(projectRole);
    }
}
