package br.com.kyw.project_kyw.application.services.project;

import br.com.kyw.project_kyw.application.repositories.ProjectRepository;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {

    private final ProjectRepository projectRepository;

    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }


}
