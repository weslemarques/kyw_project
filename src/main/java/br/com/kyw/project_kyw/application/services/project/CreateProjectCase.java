package br.com.kyw.project_kyw.application.services.project;

import br.com.kyw.project_kyw.adapters.dtos.emails.Email;
import br.com.kyw.project_kyw.adapters.dtos.request.ProjectCreateDTO;
import br.com.kyw.project_kyw.adapters.dtos.response.ProjectResponseDTO;
import br.com.kyw.project_kyw.application.exceptions.UserNotFoundExeception;
import br.com.kyw.project_kyw.application.repositories.ProjectRepository;
import br.com.kyw.project_kyw.application.repositories.UserRepository;
import br.com.kyw.project_kyw.application.services.utils.FileStorageService;
import br.com.kyw.project_kyw.application.services.utils.SendNotification;
import br.com.kyw.project_kyw.core.entities.Project;
import br.com.kyw.project_kyw.core.entities.User;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.nio.file.Path;
import java.util.Optional;

@Component
public class CreateProjectCase {
    private final UserRepository userRepository;
    private final ModelMapper mapper;
    private final ProjectRepository projectRepository;
    private final SendNotification sendNotification;
    private final FileStorageService fileStorageService;


    public CreateProjectCase(UserRepository userRepository, ProjectRepository projectRepository, ModelMapper mapper, SendNotification sendInvitation, FileStorageService fileStorageService) {
        this.userRepository = userRepository;
        this.projectRepository = projectRepository;
        this.mapper = mapper;
        this.sendNotification = sendInvitation;
        this.fileStorageService = fileStorageService;
    }
    public ProjectResponseDTO createProject(ProjectCreateDTO projectRequest){
        var subject = "Convite para o projeto " + projectRequest.getName();
        var text = "Voçê foi convidado para entrar no projeto " + projectRequest.getName();
        Project projectEntity = mapper.map(projectRequest, Project.class);
        projectEntity.addAdmin(getUserAutentication());
        for (String email: projectRequest.getMembers()) {
            Optional<User> user  = userRepository.findByEmail(email);
            user.ifPresent(value -> sendNotification
                    .senderByEmail(new Email(value.getId(), value.getEmail(), subject, text)));
        }
        Path pathUrlImage = fileStorageService.storageFile(projectRequest.getImage());
        projectEntity.setImageUrl(pathUrlImage.toUri().getPath());
        projectEntity = projectRepository.save(projectEntity);
        return mapper.map(projectEntity, ProjectResponseDTO.class);
    }
    public User getUserAutentication(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            String email = authentication.getName();
            return userRepository
                    .findByEmail(email)
                    .orElseThrow(() -> new UserNotFoundExeception("User Not Found"));
        }
        throw new UserNotFoundExeception("User not authentication");
    }
}
