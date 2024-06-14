package br.com.kyw.project_kyw.application.repositories;

import br.com.kyw.project_kyw.core.entities.Task;
import br.com.kyw.project_kyw.core.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public interface TaskRepository extends JpaRepository<Task, UUID> {
    @Query("SELECT t FROM Task t WHERE t.deadline >= :hoje ORDER BY t.deadline ASC")
    List<Task> getByDeadline(Date hoje);

    @Query("SELECT t FROM Task t WHERE t.createAt BETWEEN :startDate AND :endDate AND t.status = :status")
    List<Task> findTasksFilterAndCreated(Instant startDate, Instant endDate, Status status);
}
