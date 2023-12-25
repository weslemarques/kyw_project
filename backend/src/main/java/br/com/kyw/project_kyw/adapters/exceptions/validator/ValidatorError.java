package br.com.kyw.project_kyw.adapters.exceptions.validator;

import br.com.kyw.project_kyw.adapters.exceptions.StandardError;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ValidatorError extends StandardError {

    private List<FieldMessage> errors = new ArrayList<>();

    public void addError(String field, String message) {
        errors.add(new FieldMessage(field, message));
    }
}
