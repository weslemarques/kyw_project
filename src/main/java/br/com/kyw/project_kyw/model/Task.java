package br.com.kyw.project_kyw.model;

import br.com.kyw.project_kyw.enums.Status;

import java.util.Date;
import java.util.UUID;

public class Task {
    private UUID id;
    private Status status;
    private boolean isImportant;
    private String description;
    private String attachments;
    private Date createAt;
    private Date completedAt;
    private Project project;
    private User attributed;

}
