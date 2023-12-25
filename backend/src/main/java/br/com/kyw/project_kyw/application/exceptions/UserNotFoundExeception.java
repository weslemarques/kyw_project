package br.com.kyw.project_kyw.application.exceptions;

public class UserNotFoundExeception extends RuntimeException{

    public UserNotFoundExeception(String message) {
        super(message);
    }
}
