package br.com.kyw.project_kyw.core.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Entity
@Table(name = "TB_USERS")
@EqualsAndHashCode(of = "id")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String username;

    private String email;
    private String password;
    private String phone;
    @OneToMany
    private List<Notification> notification = new ArrayList<>();

    @ManyToMany
    @JoinTable( name = "TB_PROJECTS_USERS",joinColumns = @JoinColumn(name = "USER_ID"), inverseJoinColumns = @JoinColumn(name ="PROJECT_ID"))
    private List<Project> projects = new ArrayList<>();

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
    public void createProject(){

    }
    public void setUsername(String username) {
        if(username != null)this.username = username;
    }

    public void setEmail(String email) {
        if(email != null) this.email = email;
    }

    public void setPassword(String password) {

        if(password != null) this.password = password;
    }

    public void setPhone(String phone) {

        if(phone != null) this.phone = phone;
    }

    public void addNotification(Notification notification) {

        if(notification != null) this.notification.add(notification);
    }

    public void addProject(Project project) {

        if(project != null)this.projects.add(project);
    }

    public void addTask(Task assignedTasks) {

        if(assignedTasks != null) this.assignedTasks.add(assignedTasks);
    }
}
