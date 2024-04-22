package br.com.kyw.project_kyw.application.repositories;

import br.com.kyw.project_kyw.core.entities.Message;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MessageRepository extends JpaRepository<Message, UUID> {

    Page<Message> findAllByOrderBySentInAsc(Pageable pageable);

    Page<Message> findAllByOrderBySentInDesc(Pageable pageable);

}
