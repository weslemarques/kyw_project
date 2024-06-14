package br.com.kyw.project_kyw.core.entities;

import br.com.kyw.project_kyw.core.enums.Criticality;
import br.com.kyw.project_kyw.core.enums.Status;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.ArrayList;
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
    private String title;
    @Enumerated(EnumType.STRING)
    private Status status;
    private Criticality criticality;
    @Size(max = 1000)
    private String description;
    @Setter(AccessLevel.NONE)
    @ElementCollection
    private List<String> attachmentsUrls = new ArrayList<>();
    private Instant createAt = Instant.now();
    private Date deadline;
    private Date completedAt;
    @ManyToOne
    private Project project;
    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "assignedTasks", cascade = CascadeType.MERGE)
    private List<User> attributedTo = new ArrayList<>();

    @Setter
    private boolean pin;


    public Task(Status status, Criticality criticality, String description, Instant createAt, Date completedAt, Project project, List<User> attributed) {
        this.status = status;
        this.criticality = criticality;
        this.description = description;
        this.createAt = createAt;
        this.completedAt = completedAt;
        this.project = project;
        this.attributedTo = attributed;
    }

    public Task() {

    }

    public void setDeadline(Date deadline) {
        if(project != null){
            this.deadline = deadline;
        }
    }

    public void setStatus(Status status) {
        if(status != null){
            this.status = status;
        }
    }
    public void setCriticality(Criticality criticality) {
        if(criticality != null){
            this.criticality = criticality;
        }
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

    public void setTitle(String title) {
        if(project != null){
            this.title = title;
        }
    }

    public void addUser(User user) {
        if (user != null) {
        this.attributedTo.add(user);
        user.addTask(this);
        }
    }

    public void addAttachement(String attachmentUrl) {
        if (attachmentUrl != null) {
            this.attachmentsUrls.add(attachmentUrl);
        }
    }


}
