package br.com.kyw.project_kyw.application.services;

import br.com.kyw.project_kyw.adapters.dtos.response.ProjectRoleResponse;
import br.com.kyw.project_kyw.application.repositories.ProjectRoleRepository;
import br.com.kyw.project_kyw.application.repositories.UserRepository;
import br.com.kyw.project_kyw.core.entities.ProjectRole;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.lang.module.ResolutionException;
import java.util.UUID;

@Service
public class ProjectRoleService {
    private final ProjectRoleRepository projectRoleRepository;
    private final UserRepository userRepository;
    private final ModelMapper mapper;

    public ProjectRoleService(ProjectRoleRepository projectRoleRepository, ModelMapper mapper,UserRepository userRepository) {
        this.projectRoleRepository = projectRoleRepository;
        this.mapper = mapper;
        this.userRepository = userRepository;
    }

    public Page<ProjectRoleResponse> getProjectRoleByUser(UUID userId, Pageable pageable){ // TODO pegar ID pela autenticação
        var pageProRole = projectRoleRepository.findProjectRolesByUser_Id(userId, pageable);
        return pageProRole.map(pRole -> mapper.map(pRole, ProjectRoleResponse.class));
    }

    public String getProjectRoleByProjectAndUser(UUID projectId, UUID userId){ //TODO fazer utilizando autenticaçõa
        ProjectRole projectRole = projectRoleRepository.findProjectRoleByProject_IdAndUser_Id(projectId, userId)
                .orElseThrow(() -> new ResolutionException("user has no role in this project"));
        return projectRole.getTitle().toString();
    }

    public void changeRoleOfAMember(){

    }
}
