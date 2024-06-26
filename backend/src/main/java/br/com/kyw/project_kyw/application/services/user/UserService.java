package br.com.kyw.project_kyw.application.services.user;

import br.com.kyw.project_kyw.adapters.dtos.base.ProjectBaseDTO;
import br.com.kyw.project_kyw.adapters.dtos.response.TaskResponse;
import br.com.kyw.project_kyw.adapters.dtos.response.UserResponseDTO;
import br.com.kyw.project_kyw.adapters.dtos.response.UserWithProjectsDTO;
import br.com.kyw.project_kyw.application.exceptions.ResourceNotFound;
import br.com.kyw.project_kyw.application.exceptions.UserNotFoundExeception;
import br.com.kyw.project_kyw.application.repositories.ProjectRepository;
import br.com.kyw.project_kyw.application.repositories.ProjectRoleRepository;
import br.com.kyw.project_kyw.application.repositories.UserRepository;
import br.com.kyw.project_kyw.application.services.utils.Mapper;
import br.com.kyw.project_kyw.core.entities.Project;
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
    private final ModelMapper modelMapper;
    private final Mapper mapper;

    public UserService(UserRepository userRepository, ProjectRoleRepository projectRoleRepository, ModelMapper modelMapper, Mapper mapper) {
        this.userRepository = userRepository;
        this.projectRoleRepository = projectRoleRepository;
        this.modelMapper = modelMapper;
        this.mapper = mapper;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Sem usuário encontrado do o email " + email));
    }

    @Transactional
    public void exitProject(UUID projectId){
        var user = userRepository.findById(Auth.getUserAuthenticate().getId()).orElseThrow(()-> new ResourceNotFound("Usuário não encontrado"));
        user.getProjects().removeIf(p -> p.getId().equals(projectId));
        userRepository.save(user);
        projectRoleRepository.deleteProjectRole(Auth.getUserAuthenticate().getId(), projectId);
    }


    public Page<UserResponseDTO> getAll(Pageable pageable){
       Page<User> user  = userRepository.findAll(pageable);
       return user.map(u -> modelMapper.map(u, UserResponseDTO.class));

    }

    public UserResponseDTO getById(UUID userId) {
       var user = userRepository.findById(userId)
               .orElseThrow(() -> new UserNotFoundExeception("Usuário não encontrado"));
       return modelMapper.map(user, UserResponseDTO.class);
    }
    public UserResponseDTO updateNicknameUser(String nickname) {
        User user = userRepository.findById(Auth.getUserAuthenticate()
                .getId()).orElseThrow(()-> new ResourceNotFound("Usuário não encontrado"));
        user.setNickname(nickname);
        user = userRepository.save(user);
        return modelMapper.map(user, UserResponseDTO.class);
    }


    public List<ProjectBaseDTO> getAllProjectsByUser() {
        var projects = userRepository.findAllProjectsByUserId(Auth.getUserAuthenticate().getId());
        return projects.stream().map(mapper::entityForProjectResponse).toList();
    }


    public List<TaskResponse> getTasksByUser() {
        var tasks = userRepository.findAllTasksByUserId(Auth.getUserAuthenticate().getId());
        return tasks.stream().map(t -> mapper.taskEntityForDTO(t, t.getAttachmentsUrls()) ).toList();
    }
}

