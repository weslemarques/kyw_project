package br.com.kyw.project_kyw.application.services.user;

import br.com.kyw.project_kyw.adapters.dtos.response.UserResponseDTO;
import br.com.kyw.project_kyw.application.exceptions.UserNotFoundExeception;
import br.com.kyw.project_kyw.application.repositories.ProjectRepository;
import br.com.kyw.project_kyw.application.repositories.UserRepository;
import br.com.kyw.project_kyw.core.entities.Project;
import br.com.kyw.project_kyw.core.entities.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
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
    private final ProjectRepository projectRepository;
    private final ModelMapper mapper;

    public UserService(UserRepository userRepository, ProjectRepository projectRepository, ModelMapper mapper) {
        this.userRepository = userRepository;
        this.projectRepository = projectRepository;
        this.mapper = mapper;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("No user found with email address " + email));
    }

    public void exitProject(UUID projectId, UUID userId){
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundExeception("User not found"));
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new UserNotFoundExeception("Project not found"));
        userRepository.save(user);
    }


    public Page<UserResponseDTO> getAll(Pageable pageable){
       Page<User> user  = userRepository.findAll(pageable);
       return user.map(u -> mapper.map(u, UserResponseDTO.class));

    }
}
