package br.com.kyw.project_kyw.application.services.project;

import br.com.kyw.project_kyw.adapters.dtos.request.ProjectUpadateDTO;
import br.com.kyw.project_kyw.adapters.dtos.response.ProjectResponseDTO;
import br.com.kyw.project_kyw.application.exceptions.UserNotFoundExeception;
import br.com.kyw.project_kyw.application.repositories.ProjectRepository;
import br.com.kyw.project_kyw.application.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ProjectServiceImpl {

    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;
    private final ModelMapper mapper;

    public ProjectServiceImpl(ProjectRepository projectRepository, ModelMapper mapper, UserRepository userRepository) {
        this.projectRepository = projectRepository;
        this.mapper = mapper;
        this.userRepository = userRepository;
    }
    public void delete(UUID id){
        var user  = projectRepository
                .findById(id).orElseThrow(() -> new UserNotFoundExeception("User não encontado"));
        user.setDeleted(true);
        projectRepository.save(user);
    }

    public ProjectResponseDTO update(ProjectUpadateDTO userDTO, UUID id){
        var user = projectRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundExeception("User não encontado"));
        mapper.map(userDTO, user);
        user = projectRepository.save(user);
       return mapper.map(user, ProjectResponseDTO.class);
    }


}
