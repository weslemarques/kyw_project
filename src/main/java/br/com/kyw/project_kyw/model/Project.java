package br.com.kyw.project_kyw.model;

import java.util.List;
import java.util.UUID;

public class Project {

    private UUID id;
    private String name;
    private String description;
    private List<User> admins;
    private String imageUrl;
    private String linkGroup;
    private List<User> members;
}
