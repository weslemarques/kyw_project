package br.com.kyw.project_kyw.application.services.auth;

import br.com.kyw.project_kyw.adapters.dtos.request.TokenRefreshRequest;
import br.com.kyw.project_kyw.adapters.dtos.request.UserLoginDTO;
import br.com.kyw.project_kyw.adapters.dtos.response.JwtResponse;
import br.com.kyw.project_kyw.adapters.dtos.response.TokenRefreshResponse;
import br.com.kyw.project_kyw.application.exceptions.AuthenticationFailed;
import br.com.kyw.project_kyw.application.services.refreshtoken.RefreshTokenService;
import br.com.kyw.project_kyw.core.entities.RefreshToken;
import br.com.kyw.project_kyw.core.entities.Role;
import br.com.kyw.project_kyw.core.entities.User;
import br.com.kyw.project_kyw.infra.security.JwtUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthService {

    private final AuthenticationManager authenticationManager;

    private final JwtUtils jwtUtils;

    private final RefreshTokenService refreshTokenService;

    public AuthService(AuthenticationManager authenticationManager, JwtUtils jwtUtils, RefreshTokenService refreshTokenService) {
        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;
        this.refreshTokenService = refreshTokenService;
    }

    //    @Override
    public JwtResponse authentication(UserLoginDTO login) {

        Authentication authenticate;
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(login.getEmail(), login.getPassword());
        try{
            authenticate  = this.authenticationManager
                    .authenticate(usernamePasswordAuthenticationToken);
            SecurityContextHolder.getContext().setAuthentication(authenticate);
        }catch (RuntimeException ex){
            throw new AuthenticationFailed("email or password invalid");
        }

        var user = (User) authenticate.getPrincipal();
        List<String> roles = user.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList();

        String token = jwtUtils.generateJwtToken(user);
        String refreshToken = refreshTokenService.createRefreshToken(user.getId()).getToken();

        return new JwtResponse(user, token, refreshToken, roles);

    }
    public TokenRefreshResponse refreshToken(TokenRefreshRequest request) {
        String refreshToken = request.getRefreshToken();

        RefreshToken refreshTokenRequest =
                refreshTokenService.findByToken(refreshToken)
                        .map(refreshTokenService::verifyExpiration).orElseThrow(() -> new AuthenticationFailed("Wrong or non-existent token"));
        User user = refreshTokenRequest.getUser();
        refreshTokenService.detele(refreshTokenRequest);
        String token = jwtUtils.generateJwtToken(user);

        RefreshToken newRefreshToken = refreshTokenService.createRefreshToken(user.getId());
        return new TokenRefreshResponse(token, newRefreshToken.getToken());

    }
}
