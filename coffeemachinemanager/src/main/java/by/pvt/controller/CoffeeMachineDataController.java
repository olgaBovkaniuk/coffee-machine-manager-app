package by.pvt.controller;

import by.pvt.service.CoffeeMachineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.logging.Logger;

@RestController
public class CoffeeMachineDataController {

    private static Logger log = Logger.getLogger("CoffeeMachineDataController");

    @Autowired
    CoffeeMachineService coffeeMachineService;

    @PostMapping("/coffee-machine-event")
    public void createCoffeeMachineEvent(@RequestBody CoffeeMachineCmd coffeeMachineCmd) throws IOException, InterruptedException {
        coffeeMachineService.createNewCoffeeMachineEvent(coffeeMachineCmd);
        log.info("New coffee machine event: " + coffeeMachineCmd);
    }

    @PutMapping("/coffee-machine-data-event")
    public void updateNewCoffeeMachineDataEvent(@RequestBody CoffeeMachineDataCmd coffeeMachineDataCmd) throws IOException, InterruptedException {
        coffeeMachineService.updateCoffeeMachineDataEvent(coffeeMachineDataCmd);
        log.info("New coffee machine data: " + coffeeMachineDataCmd);
    }
}
