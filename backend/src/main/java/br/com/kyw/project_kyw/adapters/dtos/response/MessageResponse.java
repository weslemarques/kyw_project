package br.com.kyw.project_kyw.adapters.dtos.response;


import br.com.kyw.project_kyw.adapters.dtos.ProjectIncludeDTO;
import br.com.kyw.project_kyw.adapters.dtos.UserIncludeDTO;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@Data
public class MessageResponse implements Serializable {

    private UUID id;
    private UserIncludeDTO sender;
    private ProjectIncludeDTO project;
    private String content;
    private Date sentIn;
}
