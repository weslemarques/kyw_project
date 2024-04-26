package br.com.kyw.project_kyw.adapters.dtos.response;

import java.util.UUID;

public record UserResponseMessage(UUID userId, String nickname) {
}

