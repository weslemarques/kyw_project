package br.com.kyw.project_kyw.adapters.dtos.request;

import br.com.kyw.project_kyw.adapters.dtos.UserIncludeDTO;
import br.com.kyw.project_kyw.core.enums.Criticality;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter @Setter
@AllArgsConstructor
public class TaskRequest implements Serializable {

    private Criticality criticality;
    private Date deadline;
    private String description;
    private String attachments;
    private Date completedAt;
    private UserIncludeDTO attributedTo;
}
