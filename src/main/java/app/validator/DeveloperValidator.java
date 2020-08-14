package app.validator;

import app.model.Developer;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component()
public class DeveloperValidator implements Validator {

    public boolean supports(Class clazz) {
        return Developer.class.equals(clazz);
    }

    public void validate(Object obj, Errors e) {
        ValidationUtils.rejectIfEmpty(e, "name", "name.empty");
        Developer p = (Developer) obj;
        if (p.getAge() < 0) {
            e.rejectValue("age", "age.negative");
        } else if (p.getAge() > 110) {
            e.rejectValue("age", "too.old");
        }
    }
}
