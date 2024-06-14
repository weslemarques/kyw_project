package br.com.kyw.project_kyw.adapters.dtos.request;

import br.com.kyw.project_kyw.core.enums.Criticality;
import br.com.kyw.project_kyw.core.enums.Status;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class TaskRequest implements Serializable {

    @NotBlank
    private String title;
    private Criticality criticality;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date deadline;
    @Size(max = 1000)
    private String description;
    private List<MultipartFile> attachment;
    private UUID projectId;
    private List<UUID> attributedTo;
    private Status status;

}
