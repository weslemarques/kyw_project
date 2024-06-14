package br.com.kyw.project_kyw.application.services;

import br.com.kyw.project_kyw.adapters.dtos.request.TaskRequest;
import br.com.kyw.project_kyw.adapters.dtos.response.TaskResponse;
import br.com.kyw.project_kyw.application.exceptions.ResourceNotFound;
import br.com.kyw.project_kyw.application.repositories.ProjectRepository;
import br.com.kyw.project_kyw.application.repositories.TaskRepository;
import br.com.kyw.project_kyw.application.repositories.UserRepository;
import br.com.kyw.project_kyw.application.services.utils.FileStorageService;
import br.com.kyw.project_kyw.application.services.utils.Mapper;
import br.com.kyw.project_kyw.core.entities.Project;
import br.com.kyw.project_kyw.core.entities.Task;
import br.com.kyw.project_kyw.core.enums.Status;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class TaskService {
    private final TaskRepository taskRepository;
    private final Mapper mapper;

    private final FileStorageService fileStorageService;
    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;

    public TaskService(TaskRepository taskRepository, Mapper mapper, FileStorageService fileStorageService, ProjectRepository projectRepository, UserRepository userRepository) {
        this.taskRepository = taskRepository;
        this.mapper = mapper;
        this.fileStorageService = fileStorageService;
        this.projectRepository = projectRepository;
        this.userRepository = userRepository;
    }
    @Transactional
    public TaskResponse create(TaskRequest taskRequest, UUID projectId){
        Task entity = mapper.taskDTOForEntity(taskRequest);

        List<String> urlAttachements = new ArrayList<>();
        Project entityProject = projectRepository.findById(projectId)
                .orElseThrow(() -> new ResourceNotFound("Projeto não encontrado"));
        Task finalEntity = entity;
        taskRequest.getAttachment().forEach((attachment)->{
            String urlImage = fileStorageService.storageFile(attachment, "attachments");
            urlAttachements.add(urlImage);
        });
        taskRequest.getAttributedTo().forEach((userId)->{
            var userEntity = userRepository.findById(userId);
            userEntity.ifPresent(finalEntity::addUser);
        });
        entity.setProject(entityProject);
        entity = taskRepository.save(entity);
        return mapper.taskEntityForDTO(entity, urlAttachements);
    }

    public TaskResponse getById(UUID taskId){
        Task taksOptional = taskRepository.findById(taskId)
                .orElseThrow(() -> new ResourceNotFound("Tarefa não encontrada"));
        return mapper.taskEntityForDTO(taksOptional, taksOptional.getAttachmentsUrls() );
    }

    public Page<TaskResponse> getAll(Pageable pageable){
        var listTask = taskRepository.findAll(pageable);
        return listTask.map(t -> mapper.taskEntityForDTO(t, t.getAttachmentsUrls()));
    }


    public TaskResponse update(TaskRequest taskRequest, UUID taskId) {
        var task = taskRepository.findById(taskId)
                .orElseThrow(() -> new ResourceNotFound("Tarefa não encontrada"));
        return mapper.taskEntityForDTO(task,task.getAttachmentsUrls());
    }
    public List<TaskResponse> getTaskByDeadline() {
        Date today = new Date();
        var listTask = taskRepository.getByDeadline(today);
        return listTask.stream().map(t -> mapper.taskEntityForDTO(t, t.getAttachmentsUrls() )).toList();
    }

    public List<TaskResponse> getTaskCreatedByDate(Instant startDate, Instant endDate, Status status) {
            var listTask = taskRepository.findTasksFilterAndCreated(startDate, endDate, status);
            return listTask.stream().map(t -> mapper.taskEntityForDTO(t, t.getAttachmentsUrls() )).toList();

    }
    public void delete(UUID taskId) {
        taskRepository.deleteById(taskId);
    }
}
