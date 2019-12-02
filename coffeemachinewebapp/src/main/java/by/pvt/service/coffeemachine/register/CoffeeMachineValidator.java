package by.pvt.service.coffeemachine.register;

import by.pvt.pojo.CoffeeMachine;
import by.pvt.service.coffeemachine.CoffeeMachineQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.UUID;

@Component
public class CoffeeMachineValidator implements Validator {

    @Autowired
    CoffeeMachineQueryService coffeeMachineQueryService;

    @Override
    public boolean supports(Class<?> clazz) {
        return CoffeeMachine.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        CoffeeMachineRegisterCmd coffeeMachine = (CoffeeMachineRegisterCmd) target;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "coffeeMachineId", "field.required",
                "Coffee machine Id must not be empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "coffeeMachineName", "field.required",
                "Coffee machine name must not be empty");
        if (coffeeMachine.getCoffeeMachineId() == null || coffeeMachine.getCoffeeMachineId().length() != 36) {
            errors.rejectValue("coffeeMachineId", "coffeeMachineId.length",
                    "Coffee machine Id must be UUID format");
        } else
        if (coffeeMachineQueryService.findItemByUuid(UUID.fromString(coffeeMachine.getCoffeeMachineId())) != null) {
            errors.rejectValue("coffeeMachineId", "field.required",
                    "Coffee machine with this UUID is already exist!");
        }

    }
}
