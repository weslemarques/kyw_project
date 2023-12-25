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
    private String nickname;
    private String email;
    private List<String> roles;

    public JwtResponse(String token, String refreshToken, UUID id, String nickname, String email, List<String> roles) {
        this.token = token;
        this.refreshToken = refreshToken;
        this.id = id;
        this.nickname = nickname;
        this.email = email;
        this.roles = roles;
    }

    public JwtResponse(User user, String token, String refreshToken, List<String> roles) {
        this.token = token;
        this.refreshToken = refreshToken;
        this.id = user.getId();
        this.nickname = user.getNickname();
        this.email = user.getEmail();
        this.roles = roles;
    }
}



