package br.com.kyw.project_kyw.application.services;

import br.com.kyw.project_kyw.application.exceptions.ResourceNotFound;
import br.com.kyw.project_kyw.application.repositories.ProjectRepository;
import br.com.kyw.project_kyw.application.repositories.ProjectRoleRepository;
import br.com.kyw.project_kyw.application.repositories.UserRepository;
import br.com.kyw.project_kyw.core.entities.Project;
import br.com.kyw.project_kyw.core.entities.ProjectRole;
import br.com.kyw.project_kyw.core.entities.User;
import br.com.kyw.project_kyw.core.enums.Title;
import org.springframework.stereotype.Service;

import java.lang.module.ResolutionException;
import java.util.UUID;

@Service
public class ProjectUserService {

    private final UserRepository userRepository;
    private final ProjectRepository projectRepository;
    private final ProjectRoleRepository projectRoleRepository;

    public ProjectUserService(UserRepository userRepository, ProjectRepository projectRepository, ProjectRoleRepository projectRoleRepository) {
        this.userRepository = userRepository;
        this.projectRepository = projectRepository;
        this.projectRoleRepository = projectRoleRepository;
    }

    public void addUserToTheProject(UUID projectId, String email) {
        User user = userRepository.findByEmail(email).orElseThrow(
                () -> new ResourceNotFound("Usuário não encontrado"));
        Project project = projectRepository.findById(projectId).orElseThrow(
                () -> new ResourceNotFound("Projeto não encontrado"));
        projectRoleRepository.save(new ProjectRole(user, project, Title.MEMBER));
        project.addUser(user);
        projectRepository.save(project);
    }

    public void addMemberToProjectById(UUID userId, UUID projectId) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new ResolutionException("Usuário não tem papel nesse projeto"));
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResolutionException("Usuário não tem papel nesse projeto"));
        projectRoleRepository.save(new ProjectRole(user, project, Title.MEMBER));

    }
}
