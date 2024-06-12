package br.com.kyw.project_kyw.adapters.controllers;

import br.com.kyw.project_kyw.adapters.dtos.UserIncludeDTO;
import br.com.kyw.project_kyw.adapters.dtos.base.ProjectBaseDTO;
import br.com.kyw.project_kyw.adapters.dtos.request.ProjectCreateDTO;
import br.com.kyw.project_kyw.adapters.dtos.request.ProjectUpadateDTO;
import br.com.kyw.project_kyw.adapters.dtos.response.TaskResponse;
import br.com.kyw.project_kyw.application.services.project.CreateProjectCase;
import br.com.kyw.project_kyw.application.services.project.ProjectServiceImpl;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/projects")
@SecurityRequirement(name = "Bearer ")
public class ProjectController {

    private final CreateProjectCase createProjectCase;

    private final ProjectServiceImpl projectService;

    public ProjectController(CreateProjectCase createProjectCase, ProjectServiceImpl projectService) {
        this.createProjectCase = createProjectCase;
        this.projectService = projectService;
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ProjectBaseDTO> save(@Valid @ModelAttribute ProjectCreateDTO createProject) {
        var projectPersit = createProjectCase.createProject(createProject);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(projectPersit.getId())
                .toUri();
        return ResponseEntity.created(location).body(projectPersit);
    }

    @GetMapping("/{projectId}")
    public ResponseEntity<ProjectBaseDTO> getProjectById(@PathVariable UUID projectId) {
        return ResponseEntity.ok(projectService.getById(projectId));
    }


    @PutMapping("/{id}")
    public ResponseEntity<ProjectBaseDTO> update(@RequestBody ProjectUpadateDTO projectUpdate, @PathVariable UUID id) {
        var userUpdate = projectService.update(projectUpdate, id);
        return ResponseEntity.ok(userUpdate);
    }

    @DeleteMapping("/{projectId}")
    public ResponseEntity<Void> delete(@PathVariable UUID projectId) {
        projectService.delete(projectId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ResponseEntity<Page<ProjectBaseDTO>> getAllPageable(Pageable pageable) {
        return ResponseEntity.ok(projectService.getAll(pageable));
    }

    @GetMapping("/{projectId}/members")
    public ResponseEntity<List<UserIncludeDTO>> getAllMembersByUser(@PathVariable UUID projectId) {
        return ResponseEntity.ok(projectService.getAllMembersByUser(projectId));
    }

    @GetMapping("/{projectId}/tasks")
    public ResponseEntity<List<TaskResponse>> getAllTasksByProject(@PathVariable UUID projectId) {
        return ResponseEntity.ok(projectService.getAllTasksByUser(projectId));
    }
}
