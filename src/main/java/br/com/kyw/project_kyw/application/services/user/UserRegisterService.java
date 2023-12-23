package br.com.kyw.project_kyw.application.services.user;

import br.com.kyw.project_kyw.adapters.dtos.request.UserRegisterDTO;
import br.com.kyw.project_kyw.adapters.dtos.response.UserResponseDTO;
import br.com.kyw.project_kyw.application.repositories.UserRepository;
import br.com.kyw.project_kyw.core.entities.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserRegisterService {

    private final UserRepository userRepository;

    final private ModelMapper mapper;
    private final BCryptPasswordEncoder bCPasswordEncoder;
    public UserRegisterService(ModelMapper mapper, UserRepository userRepository, BCryptPasswordEncoder bCPasswordEncoder) {
        this.mapper = mapper;
        this.userRepository = userRepository;
        this.bCPasswordEncoder = bCPasswordEncoder;
    }
    public UserResponseDTO registerUser(UserRegisterDTO userRegister){
        User entity = new User();
        mapper.map(userRegister, entity);
        entity.setPassword(bCPasswordEncoder.encode(entity.getPassword()));
        entity = userRepository.save(entity);
        return mapper.map(entity, UserResponseDTO.class);
    }

}
