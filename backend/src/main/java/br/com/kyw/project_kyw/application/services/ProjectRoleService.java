package br.com.kyw.project_kyw.application.services;

import br.com.kyw.project_kyw.adapters.dtos.response.ProjectRoleResponse;
import br.com.kyw.project_kyw.application.repositories.ProjectRepository;
import br.com.kyw.project_kyw.application.repositories.ProjectRoleRepository;
import br.com.kyw.project_kyw.core.entities.Project;
import br.com.kyw.project_kyw.core.entities.ProjectRole;
import br.com.kyw.project_kyw.core.enums.Title;
import br.com.kyw.project_kyw.infra.security.Auth;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.lang.module.ResolutionException;
import java.util.Objects;
import java.util.UUID;

@Service
public class ProjectRoleService {
    private final ProjectRoleRepository projectRoleRepository;
    private final ProjectRepository projectRepository;
    private final ModelMapper mapper;
    public ProjectRoleService(ProjectRoleRepository projectRoleRepository, ModelMapper mapper, ProjectRepository projectRepository) {
        this.projectRoleRepository = projectRoleRepository;
        this.mapper = mapper;
        this.projectRepository = projectRepository;
    }

    public Page<ProjectRoleResponse> getProjectRoleByUser(Pageable pageable){
        var pageProRole = projectRoleRepository.findProjectRolesByUser_Id(Auth.getUserAuthenticate().getId(), pageable);
        return pageProRole.map(pRole -> mapper.map(pRole, ProjectRoleResponse.class));
    }

    public String getProjectRoleByProjectAndUser(UUID projectId){
        if(isCreator(projectId)){
            return "CREATOR";
        }
        ProjectRole projectRole = projectRoleRepository.findProjectRoleByProject_IdAndUser_Id(projectId, Auth.getUserAuthenticate().getId())
                .orElseThrow(() -> new ResolutionException("Usuário não tem papel nesse projeto"));
        return projectRole.getTitle().toString();
    }
    public void changeRoleOfAMember(Title newTitle,UUID projectId, UUID userId ){
        var userRole = projectRoleRepository.findProjectRoleByProject_IdAndUser_Id(projectId,userId).orElseThrow(() -> new ResolutionException("Usuário não tem papel nesse projeto") );
        userRole.setTitle(newTitle);
        projectRoleRepository.save(userRole);
    }
    public boolean isCreator(UUID projectId){
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new ResolutionException("Usuário não tem papel nesse projeto"));
        UUID idUserLoged = Auth.getUserAuthenticate().getId();
        return Objects.equals(idUserLoged.toString(), project.getCreator().getId().toString());
    }
}
