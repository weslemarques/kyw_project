package br.com.kyw.project_kyw.application.services.project;

import br.com.kyw.project_kyw.adapters.dtos.base.ProjectBaseDTO;
import br.com.kyw.project_kyw.adapters.dtos.emails.Email;
import br.com.kyw.project_kyw.adapters.dtos.request.ProjectCreateDTO;
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
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;

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

    @Transactional
    public ProjectBaseDTO createProject(ProjectCreateDTO projectRequest){
        User creator = Auth.getUserAuthenticate();
        Project projectEntity = mapper.dtoForProjectEntity(projectRequest);
        if(projectRequest.getImage() != null){
            String pathUrlImage = fileStorageService.storageFile(projectRequest.getImage(), "project");
            projectEntity.setImageUrl(pathUrlImage);
        }
        projectEntity.setCreator(creator);
        projectEntity = projectRepository.save(projectEntity);
        projectEntity = saveMembers(projectRequest.getMembers(), projectEntity);
        projectEntity.addMember(creator);
        ProjectRole roleAdmin = new ProjectRole(creator, projectEntity, Title.CREATOR);
        projectRoleRepository.save(roleAdmin);
        projectRepository.save(projectEntity);
        return mapper.entityForProjectResponse(projectEntity);
    }

    @Transactional
    public Project saveMembers(List<String> memberEmails, Project projectEntity){
        memberEmails.forEach(email ->  {
            var userOptional  = userRepository.findByEmail(email);
            userOptional.ifPresent(user -> {
                sendNotification
                        .senderByEmail(new Email(user.getId(), user.getEmail(), subject, text));
                    projectEntity.addMember(user);
                    projectRoleRepository.save(new ProjectRole(user, projectEntity, Title.MEMBER));
                    }
            );
        });

        return projectEntity;
    }

}
