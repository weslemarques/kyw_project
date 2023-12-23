package br.com.kyw.project_kyw.application.services.project;

import br.com.kyw.project_kyw.adapters.dtos.request.ProjectCreateDTO;
import br.com.kyw.project_kyw.adapters.dtos.response.UserResponseDTO;
import br.com.kyw.project_kyw.application.SendInvitation;
import br.com.kyw.project_kyw.application.exceptions.UserNotFoundExeception;
import br.com.kyw.project_kyw.application.repositories.ProjectRepository;
import br.com.kyw.project_kyw.application.repositories.UserRepository;
import br.com.kyw.project_kyw.core.entities.Project;
import br.com.kyw.project_kyw.core.entities.User;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.swing.text.html.Option;
import java.util.Optional;

@Component
public class CreateProjectCase {
    private final UserRepository userRepository;
    private final ModelMapper mapper;
    private final ProjectRepository projectRepository;


    public CreateProjectCase(UserRepository userRepository, ProjectRepository projectRepository, ModelMapper mapper) {
        this.userRepository = userRepository;
        this.projectRepository = projectRepository;
        this.mapper = mapper;
    }

    public UserResponseDTO createProject(ProjectCreateDTO projectRequest){
        User authenticatedUser = getUserAutentication();
        Project projectEntity = new Project();
        mapper.map(projectRequest, projectEntity);
        projectEntity.addAdmin(authenticatedUser);


        return null;
    }


    public User getUserAutentication(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        return userRepository
                .findByEmail(email)
                .orElseThrow(() -> new UserNotFoundExeception("User Not Found"));
    }


}
