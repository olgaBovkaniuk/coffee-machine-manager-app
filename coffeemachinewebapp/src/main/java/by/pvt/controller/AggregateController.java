package by.pvt.controller;

import by.pvt.service.aggregate.AggregateService;
import by.pvt.updater.EventRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

@Controller
@RequestMapping("/aggregate")
public class AggregateController {

    private Logger log = Logger.getLogger("AggregateController is calling..");

    @Autowired
    AggregateService aggregateService;

    @GetMapping("/find")
    public String showFindAggregatePage() {
        return "findAggregatePage";
    }

    @GetMapping("/events")
    public String showAggregateList(
            Model model,
            @RequestParam(value = "aggregateId")  UUID aggregateId
            ) {
        ModelMap modelMap = new ModelMap();
        List<EventRecord> eventsByUuid = aggregateService.findEventsByUuid(aggregateId);
        modelMap.addAttribute("aggregateEvents", eventsByUuid);
        modelMap.addAttribute("aggregateStateDto", aggregateService.getAggregateState(eventsByUuid));
        model.addAllAttributes(modelMap);
        return "aggregateListPage";
    }
}
