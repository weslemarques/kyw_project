package br.com.kyw.project_kyw.infra.security;

import br.com.kyw.project_kyw.application.exceptions.ResourceNotFound;
import br.com.kyw.project_kyw.core.entities.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class Auth {
    public static User getUserAuthenticate(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(!authentication.isAuthenticated()){
            throw new ResourceNotFound("Usuário não autenticado");
        }
            return (User) authentication.getPrincipal();
    }
}
