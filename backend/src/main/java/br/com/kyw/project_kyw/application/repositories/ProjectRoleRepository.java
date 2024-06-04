package br.com.kyw.project_kyw.application.repositories;

import br.com.kyw.project_kyw.core.entities.ProjectRole;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.UUID;

public interface ProjectRoleRepository extends JpaRepository<ProjectRole, UUID> {

    @Modifying
    @Query("delete from ProjectRole p where p.user.id = :userId and p.project.id = :projectId")
    void deleteProjectRole(UUID userId, UUID projectId);
    Page<ProjectRole> findProjectRolesByUser_Id(UUID id, Pageable pageable);
    Optional<ProjectRole> findProjectRoleByProject_IdAndUser_Id(UUID projectId, UUID userId);

}
