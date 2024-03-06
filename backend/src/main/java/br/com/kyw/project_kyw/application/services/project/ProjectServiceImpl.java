package br.com.kyw.project_kyw.application.services.project;

import br.com.kyw.project_kyw.adapters.dtos.request.ProjectUpadateDTO;
import br.com.kyw.project_kyw.adapters.dtos.response.ProjectResponseDTO;
import br.com.kyw.project_kyw.application.exceptions.ResourceNotFound;
import br.com.kyw.project_kyw.application.exceptions.UserNotFoundExeception;
import br.com.kyw.project_kyw.application.repositories.ProjectRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.UUID;

@Service
public class ProjectServiceImpl {

    private final ProjectRepository projectRepository;
    private final ModelMapper mapper;

    public ProjectServiceImpl(ProjectRepository projectRepository, ModelMapper mapper) {
        this.projectRepository = projectRepository;
        this.mapper = mapper;
    }
    public void delete(UUID id){
        var user  = projectRepository
                .findById(id).orElseThrow(() -> new UserNotFoundExeception("User not Found"));
        user.setDeleted(true);
        projectRepository.save(user);
    }

    public ProjectResponseDTO update(ProjectUpadateDTO projectUpadateDTO, UUID id){
        var project = projectRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundExeception("User not Found")); // Para que serve essa verificação?
        mapper.map(projectUpadateDTO, project);
        project = projectRepository.save(project);
       return mapper.map(project, ProjectResponseDTO.class);
    }


        public ProjectResponseDTO getById(UUID projectId) {
            var project =  projectRepository.findById(projectId)
                    .orElseThrow(()-> new ResourceNotFound("Project Not Found"));
            return mapper.map(project, ProjectResponseDTO.class);
        }

        public Page<ProjectResponseDTO> getAll(Pageable pageable) {
            var listProject =  projectRepository.findAll(pageable);
            return listProject.map(project -> mapper.map(project, ProjectResponseDTO.class));
        }
}
