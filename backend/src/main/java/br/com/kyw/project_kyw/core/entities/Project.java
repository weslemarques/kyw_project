package br.com.kyw.project_kyw.core.entities;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Entity
@Table(name = "tb_projects")
@EqualsAndHashCode(of = "id")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String name;
    private String description;
    @ManyToOne
    private User creator;
    private String imageUrl;
    private String linkGroup;
    private boolean pin;
    @OneToMany(mappedBy = "project")
    private List<Task> tasks = new ArrayList<>();
    private Instant createAt = Instant.now();
    private boolean deleted;
    @ManyToOne
    private List<ProjectRole> projectRole;
    public Project() {
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setLinkGroup(String linkGroup) {
        this.linkGroup = linkGroup;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }


    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public void setPin(boolean pin) {
        this.pin = pin;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

}
