package br.com.kyw.project_kyw.application.services.utils;


import br.com.kyw.project_kyw.adapters.dtos.emails.Email;

public interface SendNotification {
    void senderByEmail(Email email);
}
