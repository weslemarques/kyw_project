package br.com.kyw.project_kyw.adapters.mappers;

import br.com.kyw.project_kyw.adapters.dtos.request.ProjectCreateDTO;
import br.com.kyw.project_kyw.core.entities.Project;
import org.springframework.stereotype.Component;

@Component
public class ProjectMapper {

    public void toEntity(Project project, ProjectCreateDTO dtoCreate){
        project.setName(dtoCreate.getName());
        project.setDescription(dtoCreate.getDescription());
    }
}
