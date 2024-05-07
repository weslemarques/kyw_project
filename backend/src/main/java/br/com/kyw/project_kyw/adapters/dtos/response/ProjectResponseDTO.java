package br.com.kyw.project_kyw.adapters.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.Instant;
import java.util.List;
import java.util.UUID;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProjectResponseDTO implements Serializable {

    private UUID id;
    private String name;
    private String description;
    private UserResponseProject creator;
    private String imageUrl;
    private String linkGroup;
    private boolean pin;
    private List<TaskResponse> tasks;
    private Instant createAt;

}
