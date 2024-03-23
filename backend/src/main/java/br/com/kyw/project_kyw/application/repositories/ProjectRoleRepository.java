package br.com.kyw.project_kyw.application.repositories;

import br.com.kyw.project_kyw.core.entities.ProjectRole;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ProjectRoleRepository extends JpaRepository<ProjectRole, UUID> {

    Page<ProjectRole> findProjectRolesByUser_Id(UUID id, Pageable pageable);
    Optional<ProjectRole> findProjectRoleByProject_IdAndUser_Id(UUID projectId, UUID userId);
}
