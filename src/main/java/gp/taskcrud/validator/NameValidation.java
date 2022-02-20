package gp.taskcrud.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class NameValidation implements ConstraintValidator<StartsWithAlph, String> {
    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        char ch = s.charAt(0);
        if(Character.isAlphabetic(ch)) return true;
        else return  false;
    }

}
