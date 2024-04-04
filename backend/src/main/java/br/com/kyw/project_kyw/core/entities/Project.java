package br.com.kyw.project_kyw.core.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.*;

@Getter
@Setter
@Entity
@Table(name = "tb_projects")
@EqualsAndHashCode(of = "id")
@AllArgsConstructor
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
    @OneToMany(mappedBy = "project",fetch = FetchType.EAGER)
    private List<Task> tasks = new ArrayList<>();
    private Instant createAt = Instant.now();
    private boolean deleted;
    @ManyToMany(mappedBy = "projects", fetch = FetchType.EAGER)
    private Set<User> members = new HashSet<>();
    public Project() {
    }

    public void addUser(User user) {
        this.members.add(user);
        user.addProject(this);
    }

}
