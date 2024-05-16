package br.com.kyw.project_kyw.application.services.project;

import br.com.kyw.project_kyw.adapters.dtos.request.ProjectUpadateDTO;
import br.com.kyw.project_kyw.adapters.dtos.response.ProjectResponseDTO;
import br.com.kyw.project_kyw.application.acess.AccessProjectLevel;
import br.com.kyw.project_kyw.application.exceptions.AuthorizationException;
import br.com.kyw.project_kyw.application.exceptions.ResourceNotFound;
import br.com.kyw.project_kyw.application.exceptions.UserNotFoundExeception;
import br.com.kyw.project_kyw.application.repositories.ProjectRepository;
import br.com.kyw.project_kyw.application.services.utils.Mapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;
@Service
public class ProjectServiceImpl {

    private final ProjectRepository projectRepository;
    private final Mapper mapper;
    private final AccessProjectLevel accessProjectLevel;

    public ProjectServiceImpl(ProjectRepository projectRepository, Mapper mapper, AccessProjectLevel accessProjectLevel) {
        this.projectRepository = projectRepository;
        this.mapper = mapper;
        this.accessProjectLevel = accessProjectLevel;
    }

    public void delete(UUID projectId) {
        if(accessProjectLevel.isCreator(projectId)){
            var user = projectRepository
                    .findById(projectId).orElseThrow(() -> new UserNotFoundExeception("Usuario não encontrado"));
            user.setDeleted(true);
            projectRepository.save(user);
            return;
        }
        throw new AuthorizationException("Você não tem autorização para deletar esse projeto");
    }

    public ProjectResponseDTO update(ProjectUpadateDTO projectUpadateDTO, UUID projectId) {
        if(accessProjectLevel.isCreatorOrAdmin(projectId)){
            var project = projectRepository.findById(projectId)
                    .orElseThrow(() -> new UserNotFoundExeception("Usuario não encontrado"));
            project.setDescription(projectUpadateDTO.getDescription());
            project.setName(projectUpadateDTO.getName());
            project = projectRepository.save(project);
            return mapper.entityForProjectResponse(project);
        }
        throw new AuthorizationException("Você não tem autorização para atualizar esse projeto");
    }

    public ProjectResponseDTO getById(UUID projectId) {
        if(accessProjectLevel.canAcessProject(projectId)){
            var project = projectRepository.findById(projectId)
                    .orElseThrow(() -> new ResourceNotFound("Projeto não encontrado"));
            return mapper.entityForProjectResponse(project);
        }
        throw new AuthorizationException("Você não tem autorização para acessar esse projeto");
    }

    public Page<ProjectResponseDTO> getAll(Pageable pageable) {
        var listProject = projectRepository.findAll(pageable);
        return listProject.map(mapper::entityForProjectResponse);
    }
}
