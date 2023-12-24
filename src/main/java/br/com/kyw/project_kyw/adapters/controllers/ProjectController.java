package br.com.kyw.project_kyw.adapters.controllers;

import br.com.kyw.project_kyw.adapters.dtos.request.ProjectCreateDTO;
import br.com.kyw.project_kyw.adapters.dtos.response.ProjectResponseDTO;
import br.com.kyw.project_kyw.application.services.project.CreateProjectCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/project")
public class ProjectController {

    @Autowired
    private CreateProjectCase createProjectCase;
    @PostMapping
    public ResponseEntity<ProjectResponseDTO> save(@ModelAttribute @Validated ProjectCreateDTO createProject){
        var projectPersit = createProjectCase.createProject(createProject);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(projectPersit.getId())
                .toUri();
        return ResponseEntity.created(location).body(projectPersit);
    }
}
