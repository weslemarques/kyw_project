package br.com.kyw.project_kyw.adapters.controllers;

import br.com.kyw.project_kyw.adapters.dtos.request.TokenRefreshRequest;
import br.com.kyw.project_kyw.adapters.dtos.request.UserLoginDTO;
import br.com.kyw.project_kyw.adapters.dtos.response.JwtResponse;
import br.com.kyw.project_kyw.adapters.dtos.response.TokenRefreshResponse;
import br.com.kyw.project_kyw.application.services.auth.AuthService;
import br.com.kyw.project_kyw.infra.security.JwtUtils;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin("*")
public class AuthController {

    final JwtUtils jwtUtils;

    private final AuthService authService;

    public AuthController(JwtUtils jwtUtils, AuthService authService) {
        this.jwtUtils = jwtUtils;
        this.authService = authService;
    }

    @PostMapping("/signin")
    public ResponseEntity<JwtResponse> authentication(@Valid @RequestBody UserLoginDTO login) {
        JwtResponse tokenJwt = authService.authentication(login);
        return ResponseEntity.ok(tokenJwt);
    }

    @PostMapping("/refreshtoken")
    public ResponseEntity<TokenRefreshResponse> refreshToken(@Valid @RequestBody TokenRefreshRequest refreshRequest) {
        TokenRefreshResponse responseToken = authService.refreshToken(refreshRequest);
        return ResponseEntity.ok(responseToken);

    }

}
