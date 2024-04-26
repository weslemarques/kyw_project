package br.com.kyw.project_kyw.infra.security;

import br.com.kyw.project_kyw.application.exceptions.TokenExpiredException;
import br.com.kyw.project_kyw.core.entities.Role;
import br.com.kyw.project_kyw.core.entities.User;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtils {

    @Value("${security.jwt.secret}")
    private String jwtSecret;

    @Value("${security.jwt.accessTokenExpirationMs}")
    private int tokenExpiration;

    public String generateJwtToken(User mainUser) {
        return JWT.create().withIssuer("br.com.kyw")
                .withSubject(mainUser.getEmail())
                .withClaim("id", mainUser.getId().toString())
                .withClaim("roles", mainUser.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList())
                .withExpiresAt(new Date(new Date().getTime() + tokenExpiration))
                .sign(Algorithm.HMAC256(jwtSecret));
    }

    public String getUsernameFromJwtToken(String token) {
        return JWT.decode(token).getSubject();
    }

    public boolean validateJwtToken(String authToken) {
        if (isExpired(authToken)) {
            throw new TokenExpiredException("Token Expirado");
        }
        return true;
    }

    public boolean isExpired(String jwtToken) {
        return JWT.decode(jwtToken).getExpiresAt().before(new Date());
    }
}
