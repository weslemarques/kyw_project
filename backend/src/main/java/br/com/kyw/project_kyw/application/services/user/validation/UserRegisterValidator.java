package br.com.kyw.project_kyw.application.services.user.validation;

import br.com.kyw.project_kyw.adapters.dtos.request.UserRegisterDTO;
import br.com.kyw.project_kyw.adapters.exceptions.validator.FieldMessage;
import br.com.kyw.project_kyw.application.repositories.UserRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class UserRegisterValidator implements ConstraintValidator<UserRegisterValid, UserRegisterDTO> {

    @Autowired
    private UserRepository repository;
    @Override
    public void initialize(UserRegisterValid constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(UserRegisterDTO userRegisterDTO, ConstraintValidatorContext context) {
        List<FieldMessage> list = new ArrayList<>();

        boolean emailExists = repository.existsByEmail(userRegisterDTO.getEmail());
        if(userRegisterDTO.getPhone() != null){
            boolean phoneExists = repository.existsByPhone(userRegisterDTO.getPhone());
            if (phoneExists) {
                list.add(new FieldMessage("phone", "Phone already exists"));
            }
        }
        boolean nicknameExists = repository.existsByNickname(userRegisterDTO.getNickname());

        if (emailExists) {
            list.add(new FieldMessage("email", "Email already exists"));
        }

        if (nicknameExists) {
            list.add(new FieldMessage("nickname", "Username already exists"));
        }

        for (FieldMessage e : list) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getField())
                    .addConstraintViolation();
        }
        return list.isEmpty();
    }
}
