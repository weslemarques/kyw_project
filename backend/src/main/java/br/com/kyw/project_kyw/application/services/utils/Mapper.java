package br.com.kyw.project_kyw.application.services.utils;

import br.com.kyw.project_kyw.adapters.dtos.ProjectIncludeDTO;
import br.com.kyw.project_kyw.adapters.dtos.UserIncludeDTO;
import br.com.kyw.project_kyw.adapters.dtos.request.ProjectCreateDTO;
import br.com.kyw.project_kyw.adapters.dtos.response.*;
import br.com.kyw.project_kyw.core.entities.Message;
import br.com.kyw.project_kyw.core.entities.Project;
import br.com.kyw.project_kyw.core.entities.Task;
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
        //projectResponse.setTasks(project.getTasks().stream().map(t -> mapper.map(t,TaskResponse.class)).toList());
        var creator = new UserIncludeDTO(project.getCreator().getId(), project.getCreator().getNickname());
        projectResponse.setCreator(creator);
        return projectResponse;
    }

    public Project dtoForProjectEntity(ProjectCreateDTO dto){
        var project = mapper.map(dto, Project.class);
        //project.setTasks(dto.getTasks().stream().map(t -> mapper.map(t, Task.class)).toList());
        return project;
    }

    public MessageResponse entityForDTOMessage(Message message){
        var messageResponse = mapper.map(message, MessageResponse.class);
        var userSender  = new UserIncludeDTO(message.getSender().getId(), message.getSender().getNickname());
        messageResponse.setSender(userSender);
        var project  = new ProjectIncludeDTO(message.getProject().getId(), message.getProject().getName());
        messageResponse.setProject(project);
        return messageResponse;
    }
}
