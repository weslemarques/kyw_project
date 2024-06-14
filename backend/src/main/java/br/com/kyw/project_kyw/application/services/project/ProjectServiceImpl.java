package br.com.kyw.project_kyw.application.services.project;

import br.com.kyw.project_kyw.adapters.dtos.UserIncludeDTO;
import br.com.kyw.project_kyw.adapters.dtos.base.ProjectBaseDTO;
import br.com.kyw.project_kyw.adapters.dtos.request.ProjectUpadateDTO;
import br.com.kyw.project_kyw.adapters.dtos.response.ProjectWithMembersDTO;
import br.com.kyw.project_kyw.adapters.dtos.response.ProjectWithTasksDTO;
import br.com.kyw.project_kyw.adapters.dtos.response.TaskResponse;
import br.com.kyw.project_kyw.adapters.dtos.response.UserResponseDTO;
import br.com.kyw.project_kyw.application.acess.AccessProjectLevel;
import br.com.kyw.project_kyw.application.exceptions.AuthorizationException;
import br.com.kyw.project_kyw.application.exceptions.ResourceNotFound;
import br.com.kyw.project_kyw.application.exceptions.UserNotFoundExeception;
import br.com.kyw.project_kyw.application.repositories.ProjectRepository;
import br.com.kyw.project_kyw.application.services.utils.Mapper;
import br.com.kyw.project_kyw.core.entities.Project;
import br.com.kyw.project_kyw.infra.security.Auth;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.UUID;
@Service
public class ProjectServiceImpl {

    private final ProjectRepository projectRepository;
    private final Mapper mapper;
    private final AccessProjectLevel accessProjectLevel;

    public ProjectServiceImpl(ProjectRepository projectRepository, Mapper mapper,AccessProjectLevel accessProjectLevel) {
        this.projectRepository = projectRepository;
        this.mapper = mapper;
        this.accessProjectLevel = accessProjectLevel;
    }

    public void delete(UUID projectId) {
        if (accessProjectLevel.isCreator(projectId)) {
            var user = projectRepository
                    .findById(projectId).orElseThrow(() -> new UserNotFoundExeception("Usuario não encontrado"));
            user.setDeleted(true);
            projectRepository.save(user);
            return;
        }
        throw new AuthorizationException("Você não tem autorização para deletar esse projeto");
    }

    public ProjectBaseDTO update(ProjectUpadateDTO projectUpadateDTO, UUID projectId) {
        if (accessProjectLevel.isCreatorOrAdmin(projectId)) {
            var project = projectRepository.findById(projectId)
                    .orElseThrow(() -> new UserNotFoundExeception("Usuario não encontrado"));
            project.setDescription(projectUpadateDTO.getDescription());
            project.setName(projectUpadateDTO.getName());
            project = projectRepository.save(project);
            return mapper.entityForProjectResponse(project);
        }
        throw new AuthorizationException("Você não tem autorização para atualizar esse projeto");
    }

    public ProjectBaseDTO getById(UUID projectId) {
        if (accessProjectLevel.canAcessProject(projectId)) {
            var project = projectRepository.findById(projectId)
                    .orElseThrow(() -> new ResourceNotFound("Projeto não encontrado"));
            if (project.isDeleted()) {
                throw new ResourceNotFound("Projeto não encontrado");
            }
            return mapper.entityForProjectResponse(project);
        }
        throw new AuthorizationException("Você não tem autorização para acessar esse projeto");
    }

    public Page<ProjectBaseDTO> getAll(Pageable pageable) {
        var listProject = projectRepository.findAll(pageable);
        return listProject.map(mapper::entityForProjectResponse);
    }

    public List<UserIncludeDTO> getAllMembersByUser(UUID projectId) {
        var members = projectRepository.findMembersByProjectId(projectId);
        return members.stream().map(mapper::projectForUserIncludeDTO).toList();
    }

    public List<ProjectBaseDTO> getProjectCreatedByDate(Instant startDate, Instant endDate, boolean createdByUser) {
        if (createdByUser) {
            var listProject = projectRepository.findProjectsFilterAndCreated(startDate, endDate, Auth.getUserAuthenticate().getId());
            return listProject.stream().map(mapper::entityForProjectResponse).toList();
        }

        var listProject = projectRepository.findProjectsFilter(startDate, endDate);

        return listProject.stream().map(mapper::entityForProjectResponse).toList();
    }

    public List<TaskResponse> getAllTasksByUser(UUID projectId) {
        var tasks = projectRepository.findTasksByProjectId(projectId);
        return tasks.stream().map(t -> mapper.taskEntityForDTO(t, t.getAttachmentsUrls() )).toList();
    }
}

