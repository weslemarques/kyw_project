package br.com.kyw.project_kyw.application.services.task;

import br.com.kyw.project_kyw.adapters.dtos.request.TaskRequest;
import br.com.kyw.project_kyw.adapters.dtos.response.TaskResponse;
import br.com.kyw.project_kyw.application.exceptions.ProjectNotFound;
import br.com.kyw.project_kyw.application.repositories.ProjectRepository;
import br.com.kyw.project_kyw.application.repositories.TaskRepository;
import br.com.kyw.project_kyw.core.entities.Project;
import br.com.kyw.project_kyw.core.entities.Task;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

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
                .orElseThrow(() -> new ProjectNotFound("Project Not Found"));
        entity.setProject(entityProject);
        return null;
    }
}
