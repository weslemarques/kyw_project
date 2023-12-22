package br.com.kyw.project_kyw.core.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "TB_USERS")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String username;

    private String email;
    private String password;
    private String phone;
    @OneToMany
    private List<Notification> notification;

    @ManyToMany
    @JoinTable( name = "TB_PROJECTS_USERS",joinColumns = @JoinColumn(name = "USER_ID"), inverseJoinColumns = @JoinColumn(name ="PROJECT_ID"))
    private List<Project> projects;

    @OneToMany(mappedBy = "attributedTo")
    private List<Task> assignedTasks = new ArrayList<>();

    public User(String username, String email, String password, String phone) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.phone = phone;
    }

    public User() {

    }

    public UUID getId() {
        return id;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<Notification> getNotification() {
        return notification;
    }

    public void addNotification(Notification notification) {
        this.notification.add(notification);
    }

    public List<Project> getProjects() {
        return projects;
    }

    public void addProject(Project project) {
        this.projects.add(project);
    }

    public List<Task> getAssignedTasks() {
        return assignedTasks;
    }

    public void addTask(Task assignedTasks) {
        this.assignedTasks.add(assignedTasks);
    }
}
