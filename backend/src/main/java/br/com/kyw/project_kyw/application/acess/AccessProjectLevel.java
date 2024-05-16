package br.com.kyw.project_kyw.application.acess;

import java.util.UUID;

public interface AccessProjectLevel {

    public boolean canAcessProject(UUID projectId);
    public boolean isCreator(UUID projectId);
    public boolean isCreatorOrAdmin(UUID projectId);
}
