package br.com.kyw.project_kyw.adapters.exceptions.validator;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class FieldMessage implements Serializable {

    private String field;
    private String message;
    public FieldMessage(String field, String message) {
        this.field = field;
        this.message = message;
    }
}
