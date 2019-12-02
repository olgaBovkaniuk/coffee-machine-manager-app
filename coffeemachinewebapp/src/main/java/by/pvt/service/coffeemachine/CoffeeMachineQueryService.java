package by.pvt.service.coffeemachine;

import by.pvt.converter.CoffeeMachineDataDomainToDtoConverter;
import by.pvt.converter.CoffeeMachineDomainToDtoConverter;
import by.pvt.dto.CoffeeMachineDataDto;
import by.pvt.dto.CoffeeMachineDto;
import by.pvt.pojo.CoffeeMachine;
import by.pvt.pojo.CoffeeMachineData;
import by.pvt.repository.CoffeeMachineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CoffeeMachineQueryService {

    @Autowired
    CoffeeMachineRepository coffeeMachineRepository;

    @Autowired
    CoffeeMachineDataDomainToDtoConverter cmDataConverter;

    @Autowired
    CoffeeMachineDomainToDtoConverter converter;

    @Transactional(value = Transactional.TxType.REQUIRES_NEW)
    public List<CoffeeMachineDataDto> getFirstCoffeeMachineData(int page, UUID coffeeMachineId) {
        return coffeeMachineRepository.findAllCMData(page, coffeeMachineId).stream()
                .map(cmDataConverter::convert)
                .collect(Collectors.toList());
    }

    @Transactional
    public CoffeeMachineDataDto findItem(Long id) {
        CoffeeMachineData domain = coffeeMachineRepository.findItemById(id);
        return cmDataConverter.convert(domain);
    }

    @Transactional
    public CoffeeMachineDto findItemByUuid(UUID coffeeMachineId) {
        CoffeeMachine domain = coffeeMachineRepository.findItemByUuid(coffeeMachineId);
        if (domain != null) {
            return converter.convert(domain);
        }
        return null;
    }

    @Transactional
    public List<CoffeeMachine> searchByCoffeeMachineName(String str) {
        return coffeeMachineRepository.findByCoffeeMachineName(str, 5);
    }

    @Transactional
    public List<CoffeeMachineDto> getFirstCoffeeMachine(int page) {
        return coffeeMachineRepository.findAllCM(page).stream()
                .map(converter::convert)
                .collect(Collectors.toList());
    }

    @Transactional
    public Long dataCount(UUID coffeeMachineId) {
        return coffeeMachineRepository.dataCount(coffeeMachineId);
    }

    @Transactional
    public Long dataCMCount() {
        return coffeeMachineRepository.dataCMCount();
    }
}
