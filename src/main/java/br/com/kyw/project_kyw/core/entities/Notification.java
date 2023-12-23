package br.com.kyw.project_kyw.core.entities;

import br.com.kyw.project_kyw.core.enums.TypeNotification;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.Date;
import java.util.UUID;

@Getter
@Entity
@Table(name = "TB_NOTIFICATION")
@EqualsAndHashCode(of = "id")
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Enumerated(EnumType.STRING)
    private TypeNotification type;
    private String content;
    private boolean viewed;
    private Date createdAt;


    public Notification(TypeNotification type, String content, boolean viewed, Date createdAt) {
        this.type = type;
        this.content = content;
        this.viewed = viewed;
        this.createdAt = createdAt;
    }

    public Notification() {

    }

    public void setType(TypeNotification type) {
        this.type = type;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setViewed(boolean viewed) {
        this.viewed = viewed;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
