package br.com.kyw.project_kyw.adapters.controllers;

import br.com.kyw.project_kyw.adapters.dtos.request.UserExitProjectDTO;
import br.com.kyw.project_kyw.adapters.dtos.request.UserRegisterDTO;
import br.com.kyw.project_kyw.adapters.dtos.response.UserResponseDTO;
import br.com.kyw.project_kyw.application.services.user.UserRegisterService;
import br.com.kyw.project_kyw.application.services.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/users")
public class UserController {


    private final UserRegisterService userRegisterService;
    private final UserService userService;

    public UserController(UserRegisterService userRegisterService, UserService userService) {
        this.userRegisterService = userRegisterService;
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponseDTO> save(@RequestBody @Validated UserRegisterDTO userRegister){
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
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<Page<UserResponseDTO>> getAll(Pageable pageable){
        return ResponseEntity.ok(userService.getAll(pageable));
    }


}
