package br.com.kyw.project_kyw.application.repositories;

import br.com.kyw.project_kyw.core.entities.Project;
import br.com.kyw.project_kyw.core.entities.Task;
import br.com.kyw.project_kyw.core.entities.User;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface ProjectRepository extends JpaRepository<Project, UUID> {

    @Query("SELECT p.members FROM Project p WHERE p.id = :projectId")
    List<User> findMembersByProjectId(UUID projectId);

    @Query("SELECT p.tasks FROM Project p WHERE p.id = :projectId")
    List<Task> findTasksByProjectId(UUID projectId);
}
