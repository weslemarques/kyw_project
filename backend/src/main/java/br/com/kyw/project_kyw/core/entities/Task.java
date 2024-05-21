package br.com.kyw.project_kyw.core.entities;

import br.com.kyw.project_kyw.core.enums.Criticality;
import br.com.kyw.project_kyw.core.enums.Status;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Getter
@Entity
@Table(name = "tb_tasks")
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
    private Instant createAt = Instant.now();
    private Date deadline;
    private Date completedAt;
    @ManyToOne
    private Project project;
    @ManyToMany
    private List<User> attributedTo;

    @Setter
    private boolean pin;


    public Task(Status status, Criticality criticality, String description, String attachments, Instant createAt, Date completedAt, Project project, List<User> attributed) {
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
        if(status != null){
            this.status = status;
        }

    }

    public Criticality isImportant() {
        return criticality;
    }

    public void setImportant(Criticality criticality) {
        if(criticality != null){
            this.criticality = criticality;
        }
    }

    public void setDescription(String description) {
        if(description != null){
            this.description = description;
        }
    }

    public void setAttachments(String attachments) {
        if(attachments != null){
            this.attachments = attachments;
        }
    }

    public void setCreateAt(Instant createAt) {
        if(createAt != null){
            this.createAt = createAt;
        }
    }

    public void setCompletedAt(Date completedAt) {
        if(completedAt != null){
            this.completedAt = completedAt;
        }
    }

    public void setProject(Project project) {
        if(project != null){
            this.project = project;
        }
    }

    public void addUser(User user) {
        this.attributedTo.add(user);
    }

}
