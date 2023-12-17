package br.com.kyw.project_kyw.model;

import java.util.List;
import java.util.UUID;

public class User {

    private UUID id;
    private String username;
    private String email;
    private String password;
    private String phone;
    private List<Notification> notification;

}
