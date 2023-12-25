package br.com.kyw.project_kyw.infra.sender;

import br.com.kyw.project_kyw.adapters.dtos.emails.Email;
import br.com.kyw.project_kyw.application.services.utils.SendNotification;
import org.springframework.stereotype.Component;

@Component
public class SendEmailImpl implements SendNotification {
    @Override
    public void senderByEmail(Email email) {
        System.out.println(email.toString());
    }
}
