package br.com.kyw.project_kyw.adapters.dtos.request;

import br.com.kyw.project_kyw.core.entities.User;
import br.com.kyw.project_kyw.core.enums.Criticality;
import br.com.kyw.project_kyw.core.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter @Setter
@AllArgsConstructor
public class TaskRequest implements Serializable {

    private Status status;
    private Criticality criticality;
    private Date deadline;
    private String description;
    private String attachments;
    private Date completedAt;
    private User attributedTo;
}
