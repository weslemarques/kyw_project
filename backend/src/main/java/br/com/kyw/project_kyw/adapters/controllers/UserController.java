package br.com.kyw.project_kyw.adapters.controllers;

import br.com.kyw.project_kyw.adapters.dtos.base.ProjectBaseDTO;
import br.com.kyw.project_kyw.adapters.dtos.request.UserRegisterDTO;
import br.com.kyw.project_kyw.adapters.dtos.response.TaskResponse;
import br.com.kyw.project_kyw.adapters.dtos.response.UserResponseDTO;
import br.com.kyw.project_kyw.adapters.dtos.response.UserWithProjectsDTO;
import br.com.kyw.project_kyw.application.services.project.ProjectServiceImpl;
import br.com.kyw.project_kyw.application.services.user.UserRegisterService;
import br.com.kyw.project_kyw.application.services.user.UserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/users")
@SecurityRequirement(name = "Bearer ")
public class UserController {


    private final UserRegisterService userRegisterService;
    private final UserService userService;
    private final ProjectServiceImpl projectService;

    public UserController(UserRegisterService userRegisterService, UserService userService, ProjectServiceImpl projectService) {
        this.userRegisterService = userRegisterService;
        this.userService = userService;
        this.projectService = projectService;
    }

    @PostMapping( value = "/register" ,consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<UserResponseDTO> save(@Valid @ModelAttribute UserRegisterDTO userRegister){
        var userPersist = userRegisterService.registerUser(userRegister);
       return ResponseEntity.created(ServletUriComponentsBuilder
               .fromCurrentRequest()
               .path("/{id}")
               .buildAndExpand(userPersist)
               .toUri()).body(userPersist);
    }

    @PostMapping("/exit/{projectId}")
    public ResponseEntity<Void> exitProject(@PathVariable UUID projectId){
        userService.exitProject(projectId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<Page<UserResponseDTO>> getAll(Pageable pageable){
        return ResponseEntity.ok(userService.getAll(pageable));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserResponseDTO> getById(@PathVariable UUID userId){
        return ResponseEntity.ok(userService.getById(userId));
    }

    @PatchMapping
    public ResponseEntity<UserResponseDTO> updateNickname(@RequestParam String nickname){
        return ResponseEntity.ok(userService.updateNicknameUser(nickname));
    }

    @GetMapping("/projects")
    public ResponseEntity<List<ProjectBaseDTO>> getProjectByUser() {
        return ResponseEntity.ok(userService.getAllProjectsByUser());
    }

    @GetMapping("/tasks")
    public ResponseEntity<List<TaskResponse>> getTasksInProjects() {
        return ResponseEntity.ok(userService.getTasksByUser());
    }

}
