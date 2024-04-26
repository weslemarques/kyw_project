package br.com.kyw.project_kyw.adapters.dtos.response;


import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@Data
public class MessageResponse implements Serializable {

    private UUID id;
    private UserResponseMessage sender;
    private ProjectResponseMessage project;
    private String content;
    private Date sentIn;
}
