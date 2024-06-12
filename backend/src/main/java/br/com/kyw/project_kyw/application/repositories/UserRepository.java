package br.com.kyw.project_kyw.application.repositories;

import br.com.kyw.project_kyw.core.entities.Project;
import br.com.kyw.project_kyw.core.entities.ProjectRole;
import br.com.kyw.project_kyw.core.entities.Task;
import br.com.kyw.project_kyw.core.entities.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findByEmail(String email);
    boolean existsByEmail(String email);
    boolean existsByPhone(String phone);
    boolean existsByNickname(String nickname);
    @Query("SELECT u.projects FROM User u  WHERE u.id = :userId")
    List<Project> findAllProjectsByUserId(UUID userId);

    @Query("SELECT u.assignedTasks FROM User u  WHERE u.id = :userId")
    List<Task> findAllTasksByUserId(UUID userId);
}
