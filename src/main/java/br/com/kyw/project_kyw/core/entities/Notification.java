package br.com.kyw.project_kyw.core.entities;

import br.com.kyw.project_kyw.core.enums.TypeNotification;
import jakarta.persistence.*;

import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "TB_NOTIFICATION")
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

    public UUID getId() {
        return id;
    }


    public TypeNotification getType() {
        return type;
    }

    public void setType(TypeNotification type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isViewed() {
        return viewed;
    }

    public void setViewed(boolean viewed) {
        this.viewed = viewed;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
