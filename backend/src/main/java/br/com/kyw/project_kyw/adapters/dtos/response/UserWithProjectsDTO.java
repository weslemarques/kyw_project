package br.com.kyw.project_kyw.adapters.dtos.response;

import br.com.kyw.project_kyw.adapters.dtos.base.ProjectBaseDTO;
import br.com.kyw.project_kyw.core.entities.Notification;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
public class UserWithProjectsDTO implements Serializable {

    private UUID id;
    private String nickname;

    private String avatarUrl;
    private List<Notification> notification = new ArrayList<>();

    private List<ProjectBaseDTO> projects = new ArrayList<>();
}
