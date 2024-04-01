package br.com.kyw.project_kyw.application.services.project;

import br.com.kyw.project_kyw.adapters.dtos.emails.Email;
import br.com.kyw.project_kyw.adapters.dtos.request.ProjectCreateDTO;
import br.com.kyw.project_kyw.adapters.dtos.response.ProjectResponseDTO;
import br.com.kyw.project_kyw.application.exceptions.ResourceNotFound;
import br.com.kyw.project_kyw.application.exceptions.UserNotFoundExeception;
import br.com.kyw.project_kyw.application.repositories.ProjectRepository;
import br.com.kyw.project_kyw.application.repositories.ProjectRoleRepository;
import br.com.kyw.project_kyw.application.repositories.UserRepository;
import br.com.kyw.project_kyw.application.services.utils.FileStorageService;
import br.com.kyw.project_kyw.application.services.utils.SendNotification;
import br.com.kyw.project_kyw.core.entities.Project;
import br.com.kyw.project_kyw.core.entities.ProjectRole;
import br.com.kyw.project_kyw.core.entities.User;
import br.com.kyw.project_kyw.core.enums.Title;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;

@Component
public class CreateProjectCase {
    private final UserRepository userRepository;
    private final ModelMapper mapper;
    private final SendNotification sendNotification;
    private final FileStorageService fileStorageService;
    private final ProjectRoleRepository projectRoleRepository;
    private final ProjectRepository projectRepository;

    String projectName = "";
    String subject = "Convite para o projeto " + projectName;
    String text = "Você foi convidado para entrar  no projeto" + projectName;


    public CreateProjectCase(UserRepository userRepository, ModelMapper mapper, SendNotification sendInvitation, FileStorageService fileStorageService, ProjectRepository projectRepository, ProjectRoleRepository projectRoleRepository) {
        this.userRepository = userRepository;
        this.mapper = mapper;
        this.sendNotification = sendInvitation;
        this.fileStorageService = fileStorageService;
        this.projectRepository = projectRepository;
        this.projectRoleRepository = projectRoleRepository;
    }
    public ProjectResponseDTO createProject(ProjectCreateDTO projectRequest){
        var creator = getUserAuthenticate();
        projectName = projectRequest.getName();
        Project projectEntity = mapper.map(projectRequest, Project.class);
        projectEntity.setCreator(creator);
        Path pathUrlImage = fileStorageService.storageFile(projectRequest.getImage());
        projectEntity.setImageUrl(pathUrlImage.toUri().getPath());
        projectRequest.getMembers().forEach(email ->  {
            User user  = userRepository.findByEmail(email).orElseThrow(() -> new UserNotFoundExeception("User not found"));
            sendNotification.senderByEmail(new Email(user.getId(), user.getEmail(), subject, text));
        });
        projectEntity = projectRepository.save(projectEntity);
        ProjectRole roleAdmin = new ProjectRole(creator, projectEntity, Title.ADMIN);
        projectRoleRepository.save(roleAdmin);
        return mapper.map(projectEntity, ProjectResponseDTO.class);
    }

    private User getUserAuthenticate(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = (String) authentication.getPrincipal();
        System.out.println(email);
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFound("Not user authenticate"));
    }

}
