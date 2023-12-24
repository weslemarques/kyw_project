package br.com.kyw.project_kyw.adapters.dtos.response;


import br.com.kyw.project_kyw.core.entities.User;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class JwtResponse implements Serializable {

    private String token;
    private String type = "Bearer";
    private String refreshToken;
    private UUID id;
    private String username;
    private String email;
    private List<String> roles;

    public JwtResponse(String token, String refreshToken, UUID id, String username, String email, List<String> roles) {
        this.token = token;
        this.refreshToken = refreshToken;
        this.id = id;
        this.username = username;
        this.email = email;
        this.roles = roles;
    }

    public JwtResponse(User user, String token, String refreshToken, List<String> roles) {
        this.token = token;
        this.refreshToken = refreshToken;
        this.id = user.getId();
        this.username = user.getNickname();
        this.email = user.getEmail();
        this.roles = roles;
    }
}



