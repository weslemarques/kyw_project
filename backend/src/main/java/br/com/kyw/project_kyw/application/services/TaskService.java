package br.com.kyw.project_kyw.application.services;

import br.com.kyw.project_kyw.adapters.dtos.request.TaskRequest;
import br.com.kyw.project_kyw.adapters.dtos.response.TaskResponse;
import br.com.kyw.project_kyw.application.exceptions.ResourceNotFound;
import br.com.kyw.project_kyw.application.repositories.ProjectRepository;
import br.com.kyw.project_kyw.application.repositories.TaskRepository;
import br.com.kyw.project_kyw.core.entities.Project;
import br.com.kyw.project_kyw.core.entities.Task;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class TaskService {
    private final TaskRepository taskRepository;
    private final ModelMapper mapper;

    private final ProjectRepository projectRepository;

    public TaskService(TaskRepository taskRepository, ModelMapper mapper, ProjectRepository projectRepository) {
        this.taskRepository = taskRepository;
        this.mapper = mapper;
        this.projectRepository = projectRepository;
    }
    public TaskResponse create(TaskRequest taskRequest, UUID projetctId){
        Task entity = mapper.map(taskRequest, Task.class);
        Project entityProject = projectRepository.findById(projetctId)
                .orElseThrow(() -> new ResourceNotFound("Project Not Found"));
        entity.setProject(entityProject);
        entity = taskRepository.save(entity);
        return mapper.map(entity, TaskResponse.class);
    }

    public TaskResponse getById(UUID taskId){
        var userOptional = taskRepository.findById(taskId)
                .orElseThrow(() -> new ResourceNotFound("Task not Found"));
        return mapper.map(userOptional, TaskResponse.class);
    }

    public Page<TaskResponse> getAll(Pageable pageable){
        var listTask = taskRepository.findAll(pageable);
        return listTask.map(task -> mapper.map(task, TaskResponse.class));
    }


    public TaskResponse update(TaskRequest taskRequest, UUID taskId) {
        var task = taskRepository.findById(taskId)
                .orElseThrow(() -> new ResourceNotFound("Task Not Found"));
        return mapper.map(task, TaskResponse.class);
    }
    public List<TaskResponse> getTaskByDeadline() {
        Date today = new Date();
        var listTask = taskRepository.getByDeadline(today);
        return listTask.stream().map(t -> mapper.map(t, TaskResponse.class)).toList();
    }

    public void delete(UUID taskId) {
        taskRepository.deleteById(taskId);
    }
}
