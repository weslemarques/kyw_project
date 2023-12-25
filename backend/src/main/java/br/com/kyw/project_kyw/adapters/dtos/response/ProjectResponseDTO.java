package br.com.kyw.project_kyw.adapters.dtos.response;

import br.com.kyw.project_kyw.core.entities.Task;
import br.com.kyw.project_kyw.core.entities.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProjectResponseDTO implements Serializable {

    private UUID id;
    private String name;
    private String description;
    private List<UserResponseDTO> admins = new ArrayList<>();
    private String imageUrl;
    private String linkGroup;
    private List<UserResponseDTO> members = new ArrayList<>();

    private List<Task> tasks = new ArrayList<>();

}
