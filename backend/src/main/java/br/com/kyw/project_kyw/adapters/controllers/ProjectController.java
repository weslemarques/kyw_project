package br.com.kyw.project_kyw.adapters.controllers;

import br.com.kyw.project_kyw.adapters.dtos.request.ProjectCreateDTO;
import br.com.kyw.project_kyw.adapters.dtos.request.ProjectUpadateDTO;
import br.com.kyw.project_kyw.adapters.dtos.response.ProjectResponseDTO;
import br.com.kyw.project_kyw.application.services.project.CreateProjectCase;
import br.com.kyw.project_kyw.application.services.project.ProjectServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/project")
public class ProjectController {

    private final CreateProjectCase createProjectCase;

    private final ProjectServiceImpl projectService;

    public ProjectController(CreateProjectCase createProjectCase, ProjectServiceImpl projectService) {
        this.createProjectCase = createProjectCase;
        this.projectService = projectService;
    }

    @PostMapping("/{userId}")
    public ResponseEntity<ProjectResponseDTO> save(@ModelAttribute @Validated ProjectCreateDTO createProject){
        var projectPersit = createProjectCase.createProject(createProject);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(projectPersit.getId())
                .toUri();
        return ResponseEntity.created(location).body(projectPersit);
    }


    @PutMapping("/{id}")
    public ResponseEntity<ProjectResponseDTO> update(@RequestBody ProjectUpadateDTO projectUpdate, @PathVariable UUID id){
        var userUpdate = projectService.update(projectUpdate, id);
        return ResponseEntity.ok(userUpdate);
    }


    @GetMapping("/{id}")
    public ResponseEntity<List<ProjectResponseDTO>> findAllProjectByUser(@PathVariable UUID id){
        List<ProjectResponseDTO> listOfProject =  projectService.findAllProjectByUser(id);

        return ResponseEntity.ok(listOfProject);
    }

    @PostMapping("/exit")
    public void exitProject(@RequestBody Map<String, UUID> request){
        projectService.exitProject(request.get("projectId"), request.get("userId"));
    }



}
