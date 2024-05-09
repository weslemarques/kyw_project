package br.com.kyw.project_kyw.application.services.utils;

import br.com.kyw.project_kyw.adapters.dtos.response.*;
import br.com.kyw.project_kyw.core.entities.Message;
import br.com.kyw.project_kyw.core.entities.Project;
import br.com.kyw.project_kyw.core.entities.ProjectRole;
import br.com.kyw.project_kyw.core.entities.Task;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Mapper {

    final
    ModelMapper mapper;

    public Mapper(ModelMapper mapper) {
        this.mapper = mapper;
    }

    public ProjectResponseDTO entityForProjectResponse(Project project){
        var projectResponse = mapper.map(project, ProjectResponseDTO.class);
        projectResponse.setTasks(project.getTasks().stream().map(t -> mapper.map(t,TaskResponse.class)).toList());
        return projectResponse;
    }

    public Project dtoForProjectEntity(ProjectResponseDTO dto){
        var project = mapper.map(dto, Project.class);
        project.setTasks(dto.getTasks().stream().map(t -> mapper.map(t, Task.class)).toList());
        return project;
    }

    public MessageResponse entityForDTOMessage(Message message){
        var messageResponse = mapper.map(message, MessageResponse.class);
        var userSender  = new UserResponseMessage(message.getSender().getId(), message.getSender().getNickname());
        messageResponse.setSender(userSender);
        var project  = new ProjectResponseMessage(message.getProject().getId(), message.getProject().getName());
        messageResponse.setProject(project);
        return messageResponse;
    }
}
