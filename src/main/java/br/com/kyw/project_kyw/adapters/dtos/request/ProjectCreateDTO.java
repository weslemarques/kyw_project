package br.com.kyw.project_kyw.adapters.dtos.request;

import br.com.kyw.project_kyw.core.entities.User;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
import java.util.List;


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
}
