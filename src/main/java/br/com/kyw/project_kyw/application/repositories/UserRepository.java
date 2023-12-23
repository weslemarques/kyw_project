package br.com.kyw.project_kyw.application.repositories;

import br.com.kyw.project_kyw.core.entities.Project;
import br.com.kyw.project_kyw.core.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findByEmail(String email);
    boolean existsByEmail(String email);
    boolean existsByPhone(String phone);
    boolean existsByUsername(String username);
}
