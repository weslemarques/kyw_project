package br.com.kyw.project_kyw.core.entities;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
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
    @ManyToMany( mappedBy = "projects",cascade = CascadeType.ALL)
    private List<User> admins = new ArrayList<>();
    private String imageUrl;
    private String linkGroup;
    @ManyToMany(mappedBy = "projects",cascade = CascadeType.ALL)
    private List<User> members = new ArrayList<>();
    private boolean pin;

    @OneToMany(mappedBy = "project")
    private List<Task> tasks = new ArrayList<>();
    private Instant createAt = Instant.now();
    private boolean deleted;
    public Project() {
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public void addAdmin(User userAdmin) {
        this.admins.add(userAdmin);
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setLinkGroup(String linkGroup) {
        this.linkGroup = linkGroup;
    }

    public void addMembers(User member) {
        this.members.add(member);
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public boolean isAdmin(User user){
       return admins.contains(user);
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public void setPin(boolean pin) {
        this.pin = pin;
    }
}
