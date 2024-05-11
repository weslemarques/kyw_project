package br.com.kyw.project_kyw.adapters.controllers;

import br.com.kyw.project_kyw.adapters.dtos.response.ProjectRoleResponse;
import br.com.kyw.project_kyw.application.services.ProjectRoleService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.UUID;

@RestController
@RequestMapping("/projects/roles")
public class ProjectRoleController {

    private final ProjectRoleService projectRoleService;

    public ProjectRoleController(ProjectRoleService projectRoleService) {
        this.projectRoleService = projectRoleService;
    }
    @GetMapping("/{userId}")
    public ResponseEntity<Page<ProjectRoleResponse>> getByUserId(@PathVariable UUID userId, Pageable pageable){
       var pageProRole = projectRoleService.getProjectRoleByUser(userId, pageable);
       return ResponseEntity.ok(pageProRole);
    }
    @GetMapping("/{userId}/{projectId}")
    public ResponseEntity<String> getByProjectAndUserId(@PathVariable UUID projectId){
        var projectRole = projectRoleService.getProjectRoleByProjectAndUser(projectId);
        return ResponseEntity.ok(projectRole);
    }
}
