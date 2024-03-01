package br.com.kyw.project_kyw.adapters.dtos.request;

import java.util.UUID;

public record UserExitProjectDTO(UUID userId, UUID projectId) {
}
