package br.com.kyw.project_kyw.application.services;

import br.com.kyw.project_kyw.adapters.dtos.response.UserResponseDTO;
import br.com.kyw.project_kyw.application.exceptions.ResourceNotFound;
import br.com.kyw.project_kyw.application.repositories.ProjectRepository;
import br.com.kyw.project_kyw.application.repositories.ProjectRoleRepository;
import br.com.kyw.project_kyw.application.repositories.UserRepository;
import br.com.kyw.project_kyw.core.entities.Project;
import br.com.kyw.project_kyw.core.entities.ProjectRole;
import br.com.kyw.project_kyw.core.entities.User;
import br.com.kyw.project_kyw.core.enums.Title;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.lang.module.ResolutionException;
import java.util.List;
import java.util.UUID;

@Service
public class ProjectUserService {

    private final UserRepository userRepository;
    private final ProjectRepository projectRepository;
    private final ProjectRoleRepository projectRoleRepository;
    private final ModelMapper mapper;

    public ProjectUserService(UserRepository userRepository, ProjectRepository projectRepository, ProjectRoleRepository projectRoleRepository, ModelMapper mapper) {
        this.userRepository = userRepository;
        this.projectRepository = projectRepository;
        this.projectRoleRepository = projectRoleRepository;
        this.mapper = mapper;
    }

    @Transactional
    public void addUserToTheProject(UUID projectId, String email) {
        User user = userRepository.findByEmail(email).orElseThrow(
                () -> new ResourceNotFound("Usuário não encontrado"));
        Project project = projectRepository.findById(projectId).orElseThrow(
                () -> new ResourceNotFound("Projeto não encontrado"));
        projectRoleRepository.save(new ProjectRole(user, project, Title.MEMBER));
        project.addMember(user);
        projectRepository.save(project);
    }

    @Transactional
    public void addMemberToProjectById(UUID userId, UUID projectId) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new ResolutionException("Usuário não tem papel nesse projeto"));
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResolutionException("Usuário não tem papel nesse projeto"));
        projectRoleRepository.save(new ProjectRole(user, project, Title.MEMBER));
        project.getMembers().add(user);
        projectRepository.save(project);
    }

    @Transactional
    public List<UserResponseDTO> getUsersByProject(UUID projectId){
        var project = projectRepository.findMembersByProjectId(projectId);
        System.out.println(project.getMembers());
        return project.getMembers().stream().map(member -> mapper.map(member, UserResponseDTO.class)).toList();
    }
}
