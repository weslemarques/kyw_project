package br.com.kyw.project_kyw.infra.security;

import br.com.kyw.project_kyw.application.exceptions.TokenExpiredException;
import br.com.kyw.project_kyw.application.exceptions.UserNotFoundExeception;
import br.com.kyw.project_kyw.application.repositories.UserRepository;
import br.com.kyw.project_kyw.core.entities.User;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class FilterTokenJwt extends OncePerRequestFilter {

    @Value("${cors.originPatterns}")
    private String allowedOrigin = "";
    private final JwtUtils jwtUtils;
    private final UserRepository userRepository;

    public FilterTokenJwt(JwtUtils jwtUtils, UserRepository userRepository) {
        this.userRepository = userRepository;
        this.jwtUtils = jwtUtils;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {


        try {
            String token = decodeToken(request);
            if(token != null){
                if(!jwtUtils.validateJwtToken(token))
                    throw new TokenExpiredException("Token Expirado");
                User user = recoverUser(token);
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user, null,user.getAuthorities());

                SecurityContextHolder.getContext().setAuthentication(authentication);
            }

            filterChain.doFilter(request, response);
        }catch (TokenExpiredException ex){
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            response.setCharacterEncoding("UTF-8");
            response.getWriter().println(ex.getLocalizedMessage());
        }

    }

    public User recoverUser(String token){
        String username = jwtUtils.getUsernameFromJwtToken(token);
        return  userRepository.findByEmail(username)
                .orElseThrow(() -> new UserNotFoundExeception("user not found"));
    }

    public  String decodeToken(HttpServletRequest request){
        String token = request.getHeader("Authorization");
        if(token != null){
            return token.replace("Bearer ", "");
        }
        return null;
    }
}
