package br.com.kyw.project_kyw.application.repositories;

import br.com.kyw.project_kyw.core.entities.Message;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface MessageRepository extends JpaRepository<Message, UUID> {

    @Query("SELECT m FROM Message m WHERE m.project.id = :projectId ORDER BY m.sentIn ASC")
    List<Message> findAllMessagesOrderBySentInAsc(UUID projectId, Pageable pageable);

    @Query("SELECT m FROM Message m  WHERE m.project.id = :projectId ORDER BY m.sentIn DESC")
    List<Message> findAllMessagesOrderBySentInDesc(UUID projectId,Pageable pageable);
;
}
