package br.com.kyw.project_kyw.application.exceptions;

public class AuthenticationFailed extends RuntimeException {
    public AuthenticationFailed(String message) {
        super(message);
    }
}
