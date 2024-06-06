package br.com.kyw.project_kyw.adapters.dtos.response;

import br.com.kyw.project_kyw.adapters.dtos.ProjectIncludeDTO;
import br.com.kyw.project_kyw.adapters.dtos.UserIncludeDTO;
import br.com.kyw.project_kyw.core.enums.Criticality;
import br.com.kyw.project_kyw.core.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.Instant;
import java.util.Date;
import java.util.UUID;

@Getter@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TaskResponse implements Serializable {

    private UUID id;
    private Status status;
    private Criticality criticality;
    private String description;
    private String attachments;
    private Instant createAt ;
    private Date completedAt;
    private ProjectIncludeDTO project;
    private UserIncludeDTO attributedTo;
    private boolean pin;
}
