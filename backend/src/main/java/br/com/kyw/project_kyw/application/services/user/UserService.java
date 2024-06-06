package br.com.kyw.project_kyw.application.services.user;

import br.com.kyw.project_kyw.adapters.dtos.response.UserResponseDTO;
import br.com.kyw.project_kyw.application.exceptions.ResourceNotFound;
import br.com.kyw.project_kyw.application.exceptions.UserNotFoundExeception;
import br.com.kyw.project_kyw.application.repositories.ProjectRoleRepository;
import br.com.kyw.project_kyw.application.repositories.UserRepository;
import br.com.kyw.project_kyw.core.entities.User;
import br.com.kyw.project_kyw.infra.security.Auth;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserService  implements UserDetailsService {

    private final UserRepository userRepository;
    private final ProjectRoleRepository projectRoleRepository;
    private final ModelMapper mapper;

    public UserService(UserRepository userRepository, ProjectRoleRepository projectRoleRepository, ModelMapper mapper) {
        this.userRepository = userRepository;
        this.projectRoleRepository = projectRoleRepository;
        this.mapper = mapper;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Sem usuário encontrado do o email " + email));
    }

    @Transactional
    public void exitProject(UUID projectId){
        projectRoleRepository.deleteProjectRole(Auth.getUserAuthenticate().getId(), projectId);
    }


    public Page<UserResponseDTO> getAll(Pageable pageable){
       Page<User> user  = userRepository.findAll(pageable);
       return user.map(u -> mapper.map(u, UserResponseDTO.class));

    }

    public UserResponseDTO getById(UUID userId) {
       var user = userRepository.findById(userId)
               .orElseThrow(() -> new UserNotFoundExeception("Usuário não encontrado"));
       return mapper.map(user, UserResponseDTO.class);
    }
    public UserResponseDTO updateNicknameUser(String nickname) {
        User user = userRepository.findById(Auth.getUserAuthenticate()
                .getId()).orElseThrow(()-> new ResourceNotFound("Usuário não encontrado"));
        user.setNickname(nickname);
        user = userRepository.save(user);
        return mapper.map(user, UserResponseDTO.class);
    }



}
