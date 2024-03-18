package br.com.kyw.project_kyw.core.entities;

import br.com.kyw.project_kyw.core.enums.State;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

@Getter
@Entity
@Table(name = "tb_users")
@EqualsAndHashCode(of = "id")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String nickname;

    private String email;
    private String password;
    private String phone;
    @OneToMany
    private List<Notification> notification;

    @Setter
    @Enumerated(EnumType.STRING)
    private State state;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "tb_user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private final Set<Role> roles = new HashSet<>();

    @OneToMany(mappedBy = "attributedTo")
    private List<Task> assignedTasks = new ArrayList<>();

    public User(String username, String email, String password, String phone) {
        this.nickname = username;
        this.email = email;
        this.password = password;
        this.phone = phone;
    }

    public User() {
        state = State.DISABLE;
        notification = new ArrayList<>();
    }

    public void setNickname(String nickname) {
        if(nickname != null)this.nickname = nickname;
    }

    public void setEmail(String email) {
        if(email != null) this.email = email;
    }

    public void setPassword(String password) {

        if(password != null) this.password = password;
    }

    public void setPhone(String phone) {
        if(phone != null) this.phone = phone;
    }

    public void addNotification(Notification notification) {
        if(notification != null) this.notification.add(notification);
    }
    public void addTask(Task assignedTasks) {
        if(assignedTasks != null) this.assignedTasks.add(assignedTasks);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
