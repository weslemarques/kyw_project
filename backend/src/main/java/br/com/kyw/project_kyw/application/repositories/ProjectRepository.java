package br.com.kyw.project_kyw.application.repositories;

import br.com.kyw.project_kyw.core.entities.Project;
import br.com.kyw.project_kyw.core.entities.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface ProjectRepository extends JpaRepository<Project, UUID> {

//    @Query("SELECT DISTINCT p FROM Project p " +
//            "LEFT JOIN p.admins admin " +
//                       "LEFT JOIN p.members member" +
//                      " WHERE admin.id = :userId OR member.id = :userId")
//    List<Project> findAllByUserId(UUID userId);

//    @Transactional
//    @Modifying
//    @Query("UPDATE Project p SET p.members = :members WHERE :user MEMBER OF p.members AND p.id = :projectId")
//    void removeMemberFromProject(@Param("projectId") UUID projectId, @Param("user") User user);
}
