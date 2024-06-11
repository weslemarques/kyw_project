package br.com.kyw.project_kyw.application.services.user;

import br.com.kyw.project_kyw.adapters.dtos.emails.Email;
import br.com.kyw.project_kyw.adapters.dtos.request.UserRegisterDTO;
import br.com.kyw.project_kyw.adapters.dtos.response.UserResponseDTO;
import br.com.kyw.project_kyw.application.repositories.UserRepository;
import br.com.kyw.project_kyw.application.services.utils.FileStorageService;
import br.com.kyw.project_kyw.application.services.utils.SendNotification;
import br.com.kyw.project_kyw.core.entities.Role;
import br.com.kyw.project_kyw.core.entities.User;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.nio.file.Path;

@Service
public class UserRegisterService {

    private final UserRepository userRepository;
    private final FileStorageService fileStorageService;

    final private ModelMapper mapper;
    private final BCryptPasswordEncoder bCPasswordEncoder;
    private final SendNotification sendNotification;
    public UserRegisterService(ModelMapper mapper, UserRepository userRepository, FileStorageService fileStorageService, BCryptPasswordEncoder bCPasswordEncoder, SendNotification sendNotification) {
        this.mapper = mapper;
        this.userRepository = userRepository;
        this.fileStorageService = fileStorageService;
        this.bCPasswordEncoder = bCPasswordEncoder;
        this.sendNotification = sendNotification;
    }

    public UserResponseDTO registerUser(@Validated UserRegisterDTO userRegister){
        User entity = new User();
        mapper.map(userRegister, entity);
        if(userRegister.getImage() != null){
            String pathUrlImage = fileStorageService.storageFile(userRegister.getImage(),"avatar");
            entity.setAvatarUrl(pathUrlImage);
        }
        entity.addRole(new Role("ROLE_USER"));
        entity.setPassword(bCPasswordEncoder.encode(entity.getPassword()));
        entity = userRepository.save(entity);
        sendNotification.senderByEmail(new Email(entity.getId(),entity.getEmail(),"Confirme Email","Confirme o email para ativar sua conta"));
        return mapper.map(entity, UserResponseDTO.class);
    }

}
