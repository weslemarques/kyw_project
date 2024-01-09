package br.com.kyw.project_kyw.application.services.user;

import br.com.kyw.project_kyw.application.exceptions.UserNotFoundExeception;
import br.com.kyw.project_kyw.application.repositories.ProjectRepository;
import br.com.kyw.project_kyw.application.repositories.UserRepository;
import br.com.kyw.project_kyw.core.entities.Project;
import br.com.kyw.project_kyw.core.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserService  implements UserDetailsService {

    private final UserRepository userRepository;
    private final ProjectRepository projectRepository;

    public UserService(UserRepository userRepository, ProjectRepository projectRepository) {
        this.userRepository = userRepository;
        this.projectRepository = projectRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("No user found with email address " + email));
    }

    public void exitProject(UUID projectId, UUID userId){
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundExeception("User not found"));
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new UserNotFoundExeception("Project not found"));
        userRepository.save(user);
    }
}
