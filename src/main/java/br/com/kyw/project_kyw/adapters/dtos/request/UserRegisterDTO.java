package br.com.kyw.project_kyw.adapters.dtos.request;

import br.com.kyw.project_kyw.application.services.user.validation.UserRegisterValid;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@AllArgsConstructor
@Getter
@Setter
@UserRegisterValid
public class UserRegisterDTO implements Serializable {
    private String username;
    @Email
    private String email;
    private String password;
    private String phone;

}
