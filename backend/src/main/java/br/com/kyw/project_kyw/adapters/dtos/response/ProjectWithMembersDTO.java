package br.com.kyw.project_kyw.adapters.dtos.response;

import br.com.kyw.project_kyw.adapters.dtos.UserIncludeDTO;
import br.com.kyw.project_kyw.adapters.dtos.base.ProjectBaseDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProjectWithMembersDTO extends ProjectBaseDTO{


    private Set<UserIncludeDTO> members = new HashSet<>();

}
