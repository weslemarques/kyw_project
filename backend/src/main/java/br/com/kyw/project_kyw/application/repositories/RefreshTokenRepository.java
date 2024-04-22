package br.com.kyw.project_kyw.application.repositories;

import br.com.kyw.project_kyw.core.entities.RefreshToken;
import br.com.kyw.project_kyw.core.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, UUID> {
    Optional<RefreshToken> findByToken(String token);
    Optional<RefreshToken> findByUser(User user);
}
