package br.com.kyw.project_kyw.application.repositories;

import br.com.kyw.project_kyw.core.entities.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProjectRepository extends JpaRepository<Project, UUID> {
}
