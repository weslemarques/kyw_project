package br.com.kyw.project_kyw.adapters.dtos.request;

import br.com.kyw.project_kyw.application.services.user.validation.UserRegisterValid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

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
    private String password;
    private String phone;
    private MultipartFile image;
}
