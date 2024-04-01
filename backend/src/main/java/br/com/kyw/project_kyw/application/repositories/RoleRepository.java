package br.com.kyw.project_kyw.application.repositories;

import br.com.kyw.project_kyw.core.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RoleRepository extends JpaRepository<Role, UUID> {
}
