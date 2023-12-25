package br.com.kyw.project_kyw.application.repositories;

import br.com.kyw.project_kyw.core.entities.Project;
import br.com.kyw.project_kyw.core.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface ProjectRepository extends JpaRepository<Project, UUID> {

    @Query("SELECT DISTINCT p FROM Project p " +
            "LEFT JOIN p.admins admin " +
                       "LEFT JOIN p.members member" +
                      " WHERE admin.id = :userId OR member.id = :userId")
    List<Project> findAllByUserId(UUID userId);
}
