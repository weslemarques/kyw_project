package br.com.kyw.project_kyw.adapters.controllers;

import br.com.kyw.project_kyw.adapters.dtos.request.UserExitProjectDTO;
import br.com.kyw.project_kyw.adapters.dtos.request.UserRegisterDTO;
import br.com.kyw.project_kyw.adapters.dtos.response.UserResponseDTO;
import br.com.kyw.project_kyw.application.services.user.UserRegisterService;
import br.com.kyw.project_kyw.application.services.user.UserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.UUID;

@RestController
@RequestMapping("/users")
@SecurityRequirement(name = "Bearer ")
public class UserController {


    private final UserRegisterService userRegisterService;
    private final UserService userService;

    public UserController(UserRegisterService userRegisterService, UserService userService) {
        this.userRegisterService = userRegisterService;
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponseDTO> save(@Valid @RequestBody UserRegisterDTO userRegister){
        var userPersist = userRegisterService.registerUser(userRegister);
       return ResponseEntity.created(ServletUriComponentsBuilder
               .fromCurrentRequest()
               .path("/{id}")
               .buildAndExpand(userPersist)
               .toUri()).body(userPersist);
    }

    @PostMapping("/exit")

    public ResponseEntity<Void> exitProject(@RequestBody UserExitProjectDTO exitProjectDTO){
        userService.exitProject(exitProjectDTO.projectId(), exitProjectDTO.userId());
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



}
