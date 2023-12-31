package br.com.kyw.project_kyw.application.repositories;

import br.com.kyw.project_kyw.core.entities.Project;
import br.com.kyw.project_kyw.core.entities.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findByEmail(String email);
    boolean existsByEmail(String email);
    boolean existsByPhone(String phone);
    boolean existsByNickname(String username);

//    @Transactional
//    @Modifying
//    @Query("UPDATE User u SET u.projects = :projects WHERE :project NOT MEMBER OF u.projects AND u.id = :userId")
//    void removerProjectFromUser(@Param("userId") UUID userId,@Param("project") Project project);
}
