package br.com.kyw.project_kyw.core.entities;

import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "TB_PROJECTS")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private UUID id;
    private String name;
    private String description;

    @ManyToMany
    private List<User> admins;
    private String imageUrl;
    private String linkGroup;
    @ManyToMany
    private List<User> members;

    @OneToMany(mappedBy = "project")
    private List<Task> tasks;


    public Project() {
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<User> getAdmins() {
        return admins;
    }

    public void addAdmin(User userAdmin) {
        this.admins.add(userAdmin);
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getLinkGroup() {
        return linkGroup;
    }

    public void setLinkGroup(String linkGroup) {
        this.linkGroup = linkGroup;
    }

    public List<User> getMembers() {
        return members;
    }

    public void setMembers(List<User> members) {
        this.members = members;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }
}
