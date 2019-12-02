package by.pvt.controller;

import by.pvt.service.coffeemachine.CoffeeMachineQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.logging.Logger;

//this controller is going to be deleted!!

@Controller
@RequestMapping("/coffee-machine-data")
public class CoffeeMachineDataController {

    private static Logger log = Logger.getLogger("CoffeeMachineDataController");

    @Autowired
    CoffeeMachineQueryService coffeeMachineQueryService;

    @GetMapping
    public String showCatalog(Model model) {
        log.info("showCatalog is calling");
        model.addAttribute("catalog", coffeeMachineQueryService.getFirstCoffeeMachine(0));
        return "coffeeMachineDataCatalog";
    }
}
