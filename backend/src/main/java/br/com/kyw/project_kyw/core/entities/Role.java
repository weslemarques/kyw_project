package br.com.kyw.project_kyw.core.entities;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;

import java.io.Serial;
import java.util.UUID;

@Entity
@Table(name = "tb_role")
@Getter
@EqualsAndHashCode(of = "id")
public class Role implements GrantedAuthority {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String authority;

    public Role() {

    }

    public Role(String authority) {
        this.authority = authority;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }



}