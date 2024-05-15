package br.com.kyw.project_kyw.adapters.dtos.response;

import br.com.kyw.project_kyw.core.enums.TypeNotification;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

public class NotificationDTO implements Serializable {
    private UUID id;
    @Enumerated(EnumType.STRING)
    private TypeNotification type;
    private String content;
    private boolean viewed;
    private Date createdAt;
}
