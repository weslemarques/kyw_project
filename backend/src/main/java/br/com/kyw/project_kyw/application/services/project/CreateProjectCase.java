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
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.nio.file.Path;
import java.util.Optional;
import java.util.UUID;

@Component
public class CreateProjectCase {
    private final UserRepository userRepository;
    private final ModelMapper mapper;
    private final ProjectRepository projectRepository;
    private final SendNotification sendNotification;
    private final FileStorageService fileStorageService;

    String projectName = "";
    String subject = "Convite para o projeto " + projectName;
    String text = "Você foi convidado para entrar  no projeto" + projectName;


    public CreateProjectCase(UserRepository userRepository, ProjectRepository projectRepository, ModelMapper mapper, SendNotification sendInvitation, FileStorageService fileStorageService) {
        this.userRepository = userRepository;
        this.projectRepository = projectRepository;
        this.mapper = mapper;
        this.sendNotification = sendInvitation;
        this.fileStorageService = fileStorageService;
    }
    public ProjectResponseDTO createProject(ProjectCreateDTO projectRequest){
        projectName = projectRequest.getName();
        User userAdmin = userRepository.findById(projectRequest.getUserAdmin()).orElseThrow(() -> new UserNotFoundExeception("User not found in create project"));
        Project projectEntity = mapper.map(projectRequest, Project.class);
        projectEntity.addAdmin(userAdmin);
        for (String email: projectRequest.getMembers()) {
            System.out.println(projectRequest.getMembers());
            Optional<User> user  = userRepository.findByEmail(email);
            System.out.println(user);
            user.ifPresent(projectEntity::addMembers);
            user.ifPresent(value -> sendNotification
                    .senderByEmail(new Email(value.getId(), value.getEmail(), subject, text)));
        }
        Path pathUrlImage = fileStorageService.storageFile(projectRequest.getImage());
        System.out.println(projectEntity.getMembers());
        projectEntity.setImageUrl(pathUrlImage.toUri().getPath());
        projectEntity = projectRepository.save(projectEntity);
        return mapper.map(projectEntity, ProjectResponseDTO.class);
    }

}
