package by.pvt.converter;

import by.pvt.dto.CoffeeMachineDto;
import by.pvt.pojo.CoffeeMachine;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class CoffeeMachineDomainToDtoConverter implements Converter<CoffeeMachine, CoffeeMachineDto> {

    @Override
    public CoffeeMachineDto convert(CoffeeMachine coffeeMachine) {
        return new CoffeeMachineDto(
                coffeeMachine.getEventId(),
                UUID.fromString(coffeeMachine.getCoffeeMachineId()),
                coffeeMachine.getCoffeeMachineName(),
                coffeeMachine.getStatus(),
                coffeeMachine.getImageName()
        );
    }
}
