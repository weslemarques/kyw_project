package br.com.kyw.project_kyw.core.entities;

import br.com.kyw.project_kyw.core.enums.Criticality;
import br.com.kyw.project_kyw.core.enums.Status;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;

import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "TB_TASKS")
@EqualsAndHashCode(of = "id")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Enumerated(EnumType.STRING)
    private Status status;
    private Criticality criticality;
    private String description;
    private String attachments;
    private Date createAt;
    private Date completedAt;
    @ManyToOne
    private Project project;
    @ManyToOne
    private User attributedTo;


    public Task(Status status, Criticality criticality, String description, String attachments, Date createAt, Date completedAt, Project project, User attributed) {
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
    public UUID getId() {
        return id;
    }

    public Status getStatus() {
        return status;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAttachments() {
        return attachments;
    }

    public void setAttachments(String attachments) {
        this.attachments = attachments;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public Date getCompletedAt() {
        return completedAt;
    }

    public void setCompletedAt(Date completedAt) {
        this.completedAt = completedAt;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public User getAttributed() {
        return attributedTo;
    }

    public void setAttributed(User attributed) {
        this.attributedTo = attributed;
    }
}
