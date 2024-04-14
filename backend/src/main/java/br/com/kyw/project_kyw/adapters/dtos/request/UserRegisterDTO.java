package br.com.kyw.project_kyw.adapters.dtos.request;

import br.com.kyw.project_kyw.application.services.user.validation.UserRegisterValid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;


@AllArgsConstructor
@Setter @Getter
@UserRegisterValid
@NoArgsConstructor
public class UserRegisterDTO implements Serializable {

    @NotBlank
    private String nickname;
    @Email
    private String email;
    @Min(value = 8, message = "minimum 8 characters")
    private String password;
    @Max(value = 13, message = "maximum 13 characters")
    private String phone;
}
