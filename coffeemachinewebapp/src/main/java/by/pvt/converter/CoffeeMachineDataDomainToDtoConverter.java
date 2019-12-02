package by.pvt.converter;

import by.pvt.dto.CoffeeMachineDataDto;
import by.pvt.pojo.CoffeeMachineData;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class CoffeeMachineDataDomainToDtoConverter implements Converter<CoffeeMachineData, CoffeeMachineDataDto> {

    @Override
    public CoffeeMachineDataDto convert(CoffeeMachineData coffeeMachineData) {
        return new CoffeeMachineDataDto(
                coffeeMachineData.getId(),
                coffeeMachineData.getWaterMl(),
                coffeeMachineData.getMilkMl(),
                coffeeMachineData.getCoffeeBeansGr(),
                coffeeMachineData.getUsedCoffeeContainerPortion(),
                UUID.fromString(coffeeMachineData.getCoffeeMachine().getCoffeeMachineId())
        );
    }
}
