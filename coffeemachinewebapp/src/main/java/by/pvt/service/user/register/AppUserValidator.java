package by.pvt.service.user.register;

import by.pvt.pojo.AppUser;
import by.pvt.service.user.AppUserQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class AppUserValidator implements Validator {

    @Autowired
    AppUserQueryService appUserQueryService;

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {
        AppUserRegisterCmd appUser = (AppUserRegisterCmd) target;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "field.required",
                "First name field must not be empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "field.required",
                "Last name field must not be empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "field.required",
                "Email field must not be empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "field.required",
                "Password field must not be empty");
        if (!(appUser.getEmail().contains("@"))) {
            errors.rejectValue("email", "email.required",
                    "Email must contains @");
        }
        if (appUserQueryService.isUserWithEmailExist(appUser.getEmail())) {
            errors.rejectValue("email", "email.required",
                    "User with this email is already exist!");
        }
    }
}
