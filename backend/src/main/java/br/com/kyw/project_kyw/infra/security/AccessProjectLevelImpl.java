package br.com.kyw.project_kyw.infra.security;

import br.com.kyw.project_kyw.application.acess.AccessProjectLevel;
import br.com.kyw.project_kyw.application.services.ProjectRoleService;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class AccessProjectLevelImpl implements AccessProjectLevel {

    private final ProjectRoleService projectRoleService;

    public AccessProjectLevelImpl(ProjectRoleService projectRoleService) {
        this.projectRoleService = projectRoleService;
    }

    @Override
    public boolean canAcessProject(UUID projectId) {
        var title = projectRoleService.getProjectRoleByProjectAndUser(projectId);
        return title.equals("ADMIN") || title.equals("CREATOR") || title.equals("MEMBER");
    }

    @Override
    public boolean isCreatorOrAdmin(UUID projectId) {
        var title = projectRoleService.getProjectRoleByProjectAndUser(projectId);
        return title.equals("ADMIN") || title.equals("CREATOR");
    }

    @Override
    public boolean isCreator(UUID projectId) {
        var title = projectRoleService.getProjectRoleByProjectAndUser(projectId);
        return title.equals("CREATOR");
    }

}
