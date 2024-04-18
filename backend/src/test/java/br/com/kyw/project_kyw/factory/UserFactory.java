package br.com.kyw.project_kyw.factory;

import br.com.kyw.project_kyw.core.entities.User;

public class UserFactory {


    public static User getUser(){
        return new User("Maria","maria@gmail.com","","");
    }
}
