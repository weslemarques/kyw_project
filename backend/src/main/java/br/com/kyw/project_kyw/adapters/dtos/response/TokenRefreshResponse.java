package br.com.kyw.project_kyw.adapters.dtos.response;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class TokenRefreshResponse implements Serializable {

    private String accessToken;
    private String refreshToken;
    private String type = "Bearer";

    public TokenRefreshResponse(String accessToken, String refreshToken) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }
}
