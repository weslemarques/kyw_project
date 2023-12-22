package br.com.kyw.project_kyw.application;


public interface SendInvitation {

    void senderInvitationByEmail(String email);

    void senderInvitationByUsername(String username);
}
