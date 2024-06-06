package br.com.kyw.project_kyw.application.services;

import br.com.kyw.project_kyw.adapters.dtos.request.TaskRequest;
import br.com.kyw.project_kyw.adapters.dtos.response.TaskResponse;
import br.com.kyw.project_kyw.application.exceptions.ResourceNotFound;
import br.com.kyw.project_kyw.application.repositories.ProjectRepository;
import br.com.kyw.project_kyw.application.repositories.TaskRepository;
import br.com.kyw.project_kyw.application.repositories.UserRepository;
import br.com.kyw.project_kyw.application.services.utils.Mapper;
import br.com.kyw.project_kyw.core.entities.Project;
import br.com.kyw.project_kyw.core.entities.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class TaskService {
    private final TaskRepository taskRepository;
    private final Mapper mapper;

    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;

    public TaskService(TaskRepository taskRepository, Mapper mapper, ProjectRepository projectRepository, UserRepository userRepository) {
        this.taskRepository = taskRepository;
        this.mapper = mapper;
        this.projectRepository = projectRepository;
        this.userRepository = userRepository;
    }
    public TaskResponse create(TaskRequest taskRequest, UUID projectId){
        Task entity = mapper.taskDTOForEntity(taskRequest);
        Project entityProject = projectRepository.findById(projectId)
                .orElseThrow(() -> new ResourceNotFound("Projeto não encontrado"));
        Task finalEntity = entity;
        taskRequest.getAttributedTo().forEach((user)->{
            var userEntity = userRepository.findById(user.userId());
            userEntity.ifPresent(finalEntity::addUser);
        });
        entity.setProject(entityProject);
        entity = taskRepository.save(entity);
        return mapper.taskEntityForDTO(entity);
    }

    public TaskResponse getById(UUID taskId){
        Task userOptional = taskRepository.findById(taskId)
                .orElseThrow(() -> new ResourceNotFound("Tarefa não encontrada"));
        return mapper.taskEntityForDTO(userOptional);
    }

    public Page<TaskResponse> getAll(Pageable pageable){
        var listTask = taskRepository.findAll(pageable);
        return listTask.map(mapper::taskEntityForDTO);
    }


    public TaskResponse update(TaskRequest taskRequest, UUID taskId) {
        var task = taskRepository.findById(taskId)
                .orElseThrow(() -> new ResourceNotFound("Tarefa não encontrada"));
        return mapper.taskEntityForDTO(task);
    }
    public List<TaskResponse> getTaskByDeadline() {
        Date today = new Date();
        var listTask = taskRepository.getByDeadline(today);
        return listTask.stream().map(mapper::taskEntityForDTO).toList();
    }

    public void delete(UUID taskId) {
        taskRepository.deleteById(taskId);
    }
}
