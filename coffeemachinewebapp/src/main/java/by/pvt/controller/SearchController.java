package by.pvt.controller;

import by.pvt.pojo.CoffeeMachine;
import by.pvt.pojo.CoffeeMachineData;
import by.pvt.service.coffeemachine.CoffeeMachineQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/search")
public class SearchController {

    @Autowired
    CoffeeMachineQueryService coffeeMachineDataCatalogService;

    @GetMapping
    public String search(@RequestParam("search-str") String str, Model model) {
        List<CoffeeMachine> searchResult = coffeeMachineDataCatalogService.searchByCoffeeMachineName(str);
        model.addAttribute("result", searchResult);
        return "searchResult";
    }
}
