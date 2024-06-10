package br.com.kyw.project_kyw.adapters.dtos.request;

import br.com.kyw.project_kyw.application.services.user.validation.UserRegisterValid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
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
    @NotBlank
    private String email;
    @NotBlank
    private String password;
    @NotBlank
    private String phone;
    private MultipartFile image;


}
