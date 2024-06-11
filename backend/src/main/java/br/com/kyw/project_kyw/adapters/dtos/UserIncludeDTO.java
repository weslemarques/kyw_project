package br.com.kyw.project_kyw.adapters.dtos;


import java.util.UUID;

public record UserIncludeDTO(UUID userId, String nickname, String avatarUrl) {

    public UserIncludeDTO() {
        this(null, null, null);
    }
}

