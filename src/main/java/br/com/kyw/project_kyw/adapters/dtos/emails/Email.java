package br.com.kyw.project_kyw.adapters.dtos.emails;

import java.util.UUID;

public record Email(UUID userId,
                               String emailTo,
                               String subject,
                               String text){}