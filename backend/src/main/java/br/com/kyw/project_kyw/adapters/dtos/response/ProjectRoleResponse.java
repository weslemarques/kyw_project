package br.com.kyw.project_kyw.adapters.dtos.response;

import br.com.kyw.project_kyw.adapters.dtos.base.ProjectBaseDTO;
import br.com.kyw.project_kyw.core.enums.Title;
import lombok.Data;

import java.io.Serializable;
import java.util.UUID;

@Data
public class ProjectRoleResponse implements Serializable {

    private UUID id;
    private ProjectBaseDTO project;
    private Title title;
}
