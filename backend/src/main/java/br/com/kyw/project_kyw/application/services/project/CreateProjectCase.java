package br.com.kyw.project_kyw.application.services.project;

import br.com.kyw.project_kyw.adapters.dtos.emails.Email;
import br.com.kyw.project_kyw.adapters.dtos.request.ProjectCreateDTO;
import br.com.kyw.project_kyw.adapters.dtos.response.ProjectResponseDTO;
import br.com.kyw.project_kyw.application.repositories.ProjectRepository;
import br.com.kyw.project_kyw.application.repositories.ProjectRoleRepository;
import br.com.kyw.project_kyw.application.repositories.UserRepository;
import br.com.kyw.project_kyw.application.services.utils.FileStorageService;
import br.com.kyw.project_kyw.application.services.utils.Mapper;
import br.com.kyw.project_kyw.application.services.utils.SendNotification;
import br.com.kyw.project_kyw.core.entities.Project;
import br.com.kyw.project_kyw.core.entities.ProjectRole;
import br.com.kyw.project_kyw.core.entities.User;
import br.com.kyw.project_kyw.core.enums.Title;
import br.com.kyw.project_kyw.infra.security.Auth;
import org.springframework.stereotype.Component;

import java.nio.file.Path;
import java.util.List;

@Component
public class CreateProjectCase {
    private final UserRepository userRepository;
    private final Mapper mapper;
    private final SendNotification sendNotification;
    private final FileStorageService fileStorageService;
    private final ProjectRoleRepository projectRoleRepository;
    private final ProjectRepository projectRepository;

    String projectName = "";
    String subject = "Convite para o projeto " + projectName;
    String text = "Você foi convidado para entrar  no projeto" + projectName;


    public CreateProjectCase(UserRepository userRepository, Mapper mapper, SendNotification sendInvitation, FileStorageService fileStorageService, ProjectRepository projectRepository, ProjectRoleRepository projectRoleRepository) {
        this.userRepository = userRepository;
        this.mapper = mapper;
        this.sendNotification = sendInvitation;
        this.fileStorageService = fileStorageService;
        this.projectRepository = projectRepository;
        this.projectRoleRepository = projectRoleRepository;
    }
    public ProjectResponseDTO createProject(ProjectCreateDTO projectRequest){
        User creator = Auth.getUserAuthenticate();
        projectName = projectRequest.getName();
        Project projectEntity = mapper.dtoForProjectEntity(projectRequest);
        if(projectRequest.getImage() != null){
            Path pathUrlImage = fileStorageService.storageFile(projectRequest.getImage(), "project");
            projectEntity.setImageUrl(pathUrlImage.toUri().getPath());
        }
        projectEntity.setCreator(creator);
        projectEntity = projectRepository.save(projectEntity);
        saveMembers(projectRequest.getMembers(), projectEntity);
        projectEntity.getMembers().add(creator);
        ProjectRole roleAdmin = new ProjectRole(creator, projectEntity, Title.CREATOR);
        projectRoleRepository.save(roleAdmin);
        return mapper.entityForProjectResponse(projectEntity);
    }

    public void saveMembers(List<String> memberEmails, Project projectEntity){
        memberEmails.forEach(email ->  {
            var userOptional  = userRepository.findByEmail(email);
            userOptional.ifPresent(user -> {
                sendNotification
                        .senderByEmail(new Email(user.getId(), user.getEmail(), subject, text));
                    projectEntity.getMembers().add(user);
                    projectRoleRepository.save(new ProjectRole(user, projectEntity, Title.MEMBER));
                    }

            );
        });
    }

}
