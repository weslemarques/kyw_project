package br.com.kyw.project_kyw.core.entities;

import br.com.kyw.project_kyw.core.enums.Title;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
@Entity
@Table(name = "tb_project_role")
@EqualsAndHashCode(of = "id")
public class ProjectRole {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;

    @Enumerated(EnumType.STRING)
    private Title title;

    public ProjectRole(User user, Project project, Title title) {
        this.user = user;
        this.project = project;
        this.title = title;
    }

    public ProjectRole() {
    }

}
