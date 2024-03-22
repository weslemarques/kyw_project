package br.com.kyw.project_kyw.application.services.utils;

import br.com.kyw.project_kyw.adapters.dtos.response.ProjectResponseDTO;
import br.com.kyw.project_kyw.core.entities.Project;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class Mapper {

    final
    ModelMapper mapper;

    public Mapper(ModelMapper mapper) {
        this.mapper = mapper;
    }

    public ProjectResponseDTO entityForProjectResponse(Project project){
        var projectResponse = mapper.map(project, ProjectResponseDTO.class);
        projectResponse.setProjectRoles(project.getProjectRoles());
        projectResponse.setTasks(project.getTasks());
        return projectResponse;
    }

    public Project dtoForProjectEntity(ProjectResponseDTO dto){
        var project = mapper.map(dto, Project.class);
        project.setProjectRoles(dto.getProjectRoles());
        project.setTasks(dto.getTasks());
        return project;
    }
}
