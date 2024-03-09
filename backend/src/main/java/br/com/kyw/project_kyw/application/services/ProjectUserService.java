package br.com.kyw.project_kyw.application.services;

import br.com.kyw.project_kyw.application.exceptions.ResourceNotFound;
import br.com.kyw.project_kyw.application.repositories.ProjectRepository;
import br.com.kyw.project_kyw.application.repositories.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ProjectUserService {

    private final UserRepository userRepository;
    private final ProjectRepository projectRepository;

    public ProjectUserService(UserRepository userRepository, ProjectRepository projectRepository) {
        this.userRepository = userRepository;
        this.projectRepository = projectRepository;
    }

    public void addUserToTheProject(UUID projectId, String email) {
        userRepository.findByEmail(email).orElseThrow(() -> new ResourceNotFound("User Not Found"));
        projectRepository.findById(projectId).orElseThrow(() -> new ResourceNotFound("Project Not Found"));
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(authentication.toString());
    }
}
