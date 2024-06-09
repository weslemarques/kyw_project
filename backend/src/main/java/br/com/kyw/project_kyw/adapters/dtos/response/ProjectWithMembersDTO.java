package br.com.kyw.project_kyw.adapters.dtos.response;

import br.com.kyw.project_kyw.adapters.dtos.UserIncludeDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.Instant;
import java.util.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProjectWithMembersDTO implements Serializable {

    private UUID id;
    private String name;
    private String description;
    private UserIncludeDTO creator;
    private String imageUrl;
    private String linkGroup;
    private boolean pin;
    private Instant createAt;
    private Set<UserIncludeDTO> members = new HashSet<>();

}
