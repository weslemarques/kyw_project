package br.com.kyw.project_kyw.adapters.dtos.request;

import br.com.kyw.project_kyw.core.entities.User;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProjectCreateDTO implements Serializable {

    @NotBlank
    private String name;
    private String description;
    private MultipartFile image;
    private List<String> members;
    private UUID userAdmin; // retirar e colocar metodo para obter logado
}
