package br.com.kyw.project_kyw.application.services.project;

import br.com.kyw.project_kyw.adapters.dtos.emails.Email;
import br.com.kyw.project_kyw.adapters.dtos.request.ProjectCreateDTO;
import br.com.kyw.project_kyw.adapters.dtos.response.ProjectResponseDTO;
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
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class CreateProjectCase {
    private final UserRepository userRepository;
    private final ModelMapper mapper;
    private final ProjectRoleRepository projectRoleRepository;
    private final SendNotification sendNotification;
    private final FileStorageService fileStorageService;

    String projectName = "";
    String subject = "Convite para o projeto " + projectName;
    String text = "Você foi convidado para entrar  no projeto" + projectName;


    public CreateProjectCase(UserRepository userRepository, ProjectRoleRepository projectRoleRepository, ModelMapper mapper, SendNotification sendInvitation, FileStorageService fileStorageService) {
        this.userRepository = userRepository;
        this.projectRoleRepository = projectRoleRepository;
        this.mapper = mapper;
        this.sendNotification = sendInvitation;
        this.fileStorageService = fileStorageService;
    }
    public ProjectResponseDTO createProject(ProjectCreateDTO projectRequest){
        List<ProjectRole> projectRoles = new ArrayList<>();
        projectName = projectRequest.getName();
        User userCreator = userRepository.findById(projectRequest.getUserAdmin()).orElseThrow(() -> new UserNotFoundExeception("User not found in create project"));
        Project projectEntity = mapper.map(projectRequest, Project.class);
        projectEntity.setCreator(userCreator);
        Path pathUrlImage = fileStorageService.storageFile(projectRequest.getImage());
        projectEntity.setImageUrl(pathUrlImage.toUri().getPath());
        for (String email: projectRequest.getMembers()) {
            Optional<User> user  = userRepository.findByEmail(email);
            user.ifPresent(value -> projectRoles.add(new ProjectRole(value, projectEntity, Title.MEMBER)));
            user.ifPresent(value -> sendNotification
                    .senderByEmail(new Email(value.getId(), value.getEmail(), subject, text)));
        }
        projectRoleRepository.saveAll(projectRoles);
        return mapper.map(projectEntity, ProjectResponseDTO.class);
    }

}
