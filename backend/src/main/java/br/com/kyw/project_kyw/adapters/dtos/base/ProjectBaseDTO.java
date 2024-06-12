package br.com.kyw.project_kyw.adapters.dtos.base;

import br.com.kyw.project_kyw.adapters.dtos.UserIncludeDTO;
import lombok.Data;

import java.io.Serializable;
import java.time.Instant;
import java.util.UUID;

@Data
public class ProjectBaseDTO implements Serializable {

    private UUID id;
    private String name;
    private String description;
    private UserIncludeDTO creator;
    private String imageUrl;
    private String linkGroup;
    private boolean pin;
    private Instant createAt;
}
