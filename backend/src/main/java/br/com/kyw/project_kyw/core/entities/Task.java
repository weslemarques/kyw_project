package br.com.kyw.project_kyw.core.entities;

import br.com.kyw.project_kyw.core.enums.Criticality;
import br.com.kyw.project_kyw.core.enums.Status;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.time.Instant;
import java.util.Date;
import java.util.UUID;

@Getter
@Entity
@Table(name = "tb_tasks")
@EqualsAndHashCode(of = "id")
public class Task {

    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Enumerated(EnumType.STRING)
    private Status status;
    private Criticality criticality;
    private String description;
    private String attachments;
    private Instant createAt = Instant.now();
    private Date deadline;
    private Date completedAt;
    @Getter
    @ManyToOne
    private Project project;
    @ManyToOne
    private User attributedTo;

    private boolean pin;


    public Task(Status status, Criticality criticality, String description, String attachments, Instant createAt, Date completedAt, Project project, User attributed) {
        this.status = status;
        this.criticality = criticality;
        this.description = description;
        this.attachments = attachments;
        this.createAt = createAt;
        this.completedAt = completedAt;
        this.project = project;
        this.attributedTo = attributed;
    }

    public Task() {

    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Criticality isImportant() {
        return criticality;
    }

    public void setImportant(Criticality criticality) {
        criticality = criticality;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAttachments(String attachments) {
        this.attachments = attachments;
    }

    public void setCreateAt(Instant createAt) {
        this.createAt = createAt;
    }

    public void setCompletedAt(Date completedAt) {
        this.completedAt = completedAt;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public void setAttributed(User attributed) {
        this.attributedTo = attributed;
    }

    public void setPin(boolean pin) {
        this.pin = pin;
    }
}
