package br.com.kyw.project_kyw.application.services.utils;


import br.com.kyw.project_kyw.adapters.dtos.emails.Email;
import org.springframework.scheduling.annotation.Async;

public interface SendNotification {
    @Async
    void senderByEmail(Email email);
}
