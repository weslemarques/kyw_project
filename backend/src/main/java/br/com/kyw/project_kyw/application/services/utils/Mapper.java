package br.com.kyw.project_kyw.application.services.utils;

import br.com.kyw.project_kyw.adapters.dtos.ProjectIncludeDTO;
import br.com.kyw.project_kyw.adapters.dtos.UserIncludeDTO;
import br.com.kyw.project_kyw.adapters.dtos.request.ProjectCreateDTO;
import br.com.kyw.project_kyw.adapters.dtos.request.TaskRequest;
import br.com.kyw.project_kyw.adapters.dtos.response.MessageResponse;
import br.com.kyw.project_kyw.adapters.dtos.response.ProjectResponseDTO;
import br.com.kyw.project_kyw.adapters.dtos.response.TaskResponse;
import br.com.kyw.project_kyw.core.entities.Message;
import br.com.kyw.project_kyw.core.entities.Project;
import br.com.kyw.project_kyw.core.entities.Task;
import br.com.kyw.project_kyw.core.enums.Status;
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
        var creator = new UserIncludeDTO(project.getCreator().getId(), project.getCreator().getNickname());
        projectResponse.setCreator(creator);
        return projectResponse;
    }

    public Project dtoForProjectEntity(ProjectCreateDTO dto){
        return mapper.map(dto, Project.class);
    }

    public MessageResponse entityForDTOMessage(Message message){
        var messageResponse = mapper.map(message, MessageResponse.class);
        var userSender  = new UserIncludeDTO(message.getSender().getId(), message.getSender().getNickname());
        messageResponse.setSender(userSender);
        var project  = new ProjectIncludeDTO(message.getProject().getId(), message.getProject().getName());
        messageResponse.setProject(project);
        return messageResponse;
    }

    public Task taskDTOForEntity(TaskRequest taskRequest) {
        var task = mapper.map(taskRequest, Task.class);
        task.setStatus(Status.OPEN);
        return task;

    }

    public TaskResponse taskEntityForDTO(Task entity) {
        var task = mapper.map(entity, TaskResponse.class);
        entity.getAttributedTo().forEach(user ->{
            task.setAttributedTo(new UserIncludeDTO(user.getId(), user.getNickname()));
        });
        return task;
    }
}
