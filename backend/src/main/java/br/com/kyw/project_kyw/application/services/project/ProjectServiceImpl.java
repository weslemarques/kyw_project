package br.com.kyw.project_kyw.application.services.project;

import br.com.kyw.project_kyw.adapters.dtos.request.ProjectUpadateDTO;
import br.com.kyw.project_kyw.adapters.dtos.response.ProjectResponseDTO;
import br.com.kyw.project_kyw.application.exceptions.AuthorizationException;
import br.com.kyw.project_kyw.application.exceptions.ResourceNotFound;
import br.com.kyw.project_kyw.application.exceptions.UserNotFoundExeception;
import br.com.kyw.project_kyw.application.repositories.ProjectRepository;
import br.com.kyw.project_kyw.application.services.ProjectRoleService;
import br.com.kyw.project_kyw.application.services.utils.Mapper;
import br.com.kyw.project_kyw.infra.security.Auth;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ProjectServiceImpl {

    private final ProjectRepository projectRepository;
    private final Mapper mapper;
    private final ProjectRoleService projectRoleService;

    public ProjectServiceImpl(ProjectRepository projectRepository, Mapper mapper, ProjectRoleService projectRoleService) {
        this.projectRepository = projectRepository;
        this.mapper = mapper;
        this.projectRoleService = projectRoleService;
    }

    public void delete(UUID id) {
        var user = projectRepository
                .findById(id).orElseThrow(() -> new UserNotFoundExeception("Usuario não encontrado"));
        user.setDeleted(true);
        projectRepository.save(user);
    }

    public ProjectResponseDTO update(ProjectUpadateDTO projectUpadateDTO, UUID projectId) {
        var role = projectRoleService.getProjectRoleByProjectAndUser(projectId);
        var project = projectRepository.findById(projectId)
                .orElseThrow(() -> new UserNotFoundExeception("Usuario não encontrado"));
        if(role.equalsIgnoreCase("ADMIN") || project.getCreator().getId() == Auth.getUserAuthenticate().getId()){
        project.setDescription(projectUpadateDTO.getDescription());
        project.setName(projectUpadateDTO.getName());
        project = projectRepository.save(project);
        return mapper.entityForProjectResponse(project);}
        throw new AuthorizationException("Você não tem autorização para atualizar esse projeto");
    }

    public ProjectResponseDTO getById(UUID projectId) {
        var project = projectRepository.findById(projectId)
                .orElseThrow(() -> new ResourceNotFound("Projeto não encontrado"));
        return mapper.entityForProjectResponse(project);
    }

    public Page<ProjectResponseDTO> getAll(Pageable pageable) {
        var listProject = projectRepository.findAll(pageable);
        return listProject.map(mapper::entityForProjectResponse);
    }
}
