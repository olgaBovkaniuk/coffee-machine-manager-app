package by.pvt.controller;

import by.pvt.dto.CoffeeMachineDataDto;
import by.pvt.dto.CoffeeMachineDto;
import by.pvt.pojo.CoffeeMachineData;
import by.pvt.service.FileService;
import by.pvt.service.coffeemachine.CoffeeMachineQueryService;
import by.pvt.service.coffeemachine.delete.CoffeeMachineDeleteService;
import by.pvt.service.coffeemachine.register.CoffeeMachineRegisterCmd;
import by.pvt.service.coffeemachine.register.CoffeeMachineRegisterService;
import by.pvt.service.coffeemachine.register.CoffeeMachineValidator;
import by.pvt.service.coffeemachine.start.CoffeeMachineStartService;
import by.pvt.service.coffeemachine.stop.CoffeeMachineStopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

@Controller
@RequestMapping("/coffee-machine")
public class CoffeeMachineController {

    private static Logger log = Logger.getLogger(CoffeeMachineController.class.getName());

    @Autowired
    CoffeeMachineQueryService coffeeMachineQueryService;

    @Autowired
    CoffeeMachineRegisterService coffeeMachineRegisterService;

    @Autowired
    FileService fileService;

    @Autowired
    CoffeeMachineStartService startService;

    @Autowired
    CoffeeMachineStopService stopService;

    @Autowired
    CoffeeMachineValidator coffeeMachineValidator;

    @Autowired
    CoffeeMachineDeleteService deleteService;

    @GetMapping("/add")
    public ModelAndView showAddCoffeeMachineView() {
        log.info("showAddCoffeeMachineView is calling");
        ModelAndView view = new ModelAndView("addCoffeeMachineView", "item", new CoffeeMachineRegisterCmd());
        return view;
    }

    @PostMapping("/add")
    public String submitAddCoffeeMachineForm(
            @Validated @ModelAttribute("item") CoffeeMachineRegisterCmd coffeeMachineCmd,
            @RequestParam("file") MultipartFile file,
            BindingResult result
    ) throws IOException {
        coffeeMachineValidator.validate(coffeeMachineCmd, result);
        if (result.hasErrors()) {
            return "addCoffeeMachineView";
        }

        log.info("Call submitAddCoffeeMachineForm" + coffeeMachineCmd);
        coffeeMachineCmd.setCoffeeMachineImage(file.getBytes());
        coffeeMachineCmd.setImageName(file.getOriginalFilename());
        fileService.saveFile(file, UUID.fromString(coffeeMachineCmd.getCoffeeMachineId()));

        if (!coffeeMachineRegisterService.register(coffeeMachineCmd)) {
            return "addCoffeeMachineError";
        }
        return "addCoffeeMAchineSuccess";
    }

    @GetMapping("/list")
    public String showCatalog(
            QueryParam queryParam,
            Model model) {
        log.info("showCatalog is calling");

        int currentPage = queryParam == null || queryParam.getPage() == null ? 0 : queryParam.getPage();

        ModelMap modelMap = new ModelMap();
        modelMap.addAttribute("catalog", coffeeMachineQueryService.getFirstCoffeeMachine(currentPage));
        Long dataCount = coffeeMachineQueryService.dataCMCount();
        modelMap.addAttribute("currentPage", currentPage);
        modelMap.addAttribute("pageSize", 10);
        modelMap.addAttribute("pagesCount", dataCount / 10);
        model.addAllAttributes(modelMap);

        return "coffeeMachineCatalog";
    }

    @GetMapping("/{coffeeMachineId}")
    public String showCatalogItem(
            @PathVariable UUID coffeeMachineId,
            QueryParam queryParam,
            Model model
    ) {
        log.info("showCatalogItem is calling");

        CoffeeMachineDto itemByUuid = coffeeMachineQueryService.findItemByUuid(coffeeMachineId);
        List<CoffeeMachineDataDto> coffeeMachineDataDtoList = coffeeMachineQueryService.getFirstCoffeeMachineData(0, coffeeMachineId);
        Long dataCount = coffeeMachineQueryService.dataCount(coffeeMachineId);

        ModelMap modelMap = new ModelMap();
        modelMap.addAttribute("coffeeMachine", itemByUuid);
        modelMap.addAttribute("catalog", coffeeMachineDataDtoList);
        modelMap.addAttribute("currentPage", queryParam.getPage() == null ? 0 : queryParam.getPage());
        modelMap.addAttribute("pageSize", 10);
        modelMap.addAttribute("pagesCount", dataCount / 10);
        model.addAllAttributes(modelMap);

        return "coffeeMachineView";
    }

    @GetMapping(value = "/{coffeeMachineId}/list")
    public String getCMDatas(
            @PathVariable UUID coffeeMachineId,
            QueryParam queryParam,
            Model model
    ) {
        ModelMap modelMap = new ModelMap();

        modelMap.addAttribute("catalog", coffeeMachineQueryService.getFirstCoffeeMachineData(queryParam.getPage(), coffeeMachineId));
        Long dataCount = coffeeMachineQueryService.dataCount(coffeeMachineId);

        CoffeeMachineDto itemByUuid = coffeeMachineQueryService.findItemByUuid(coffeeMachineId);
        modelMap.addAttribute("coffeeMachine", itemByUuid);
        modelMap.addAttribute("currentPage", queryParam.getPage() == null ? 0 : queryParam.getPage());
        modelMap.addAttribute("pageSize", 10);
        modelMap.addAttribute("pagesCount", dataCount / 10);

        model.addAllAttributes(modelMap);

        return "coffeeMachineView";
    }

    @GetMapping("/{coffeeMachineId}/start")
    public ModelAndView startCoffeeMachine(
            @PathVariable UUID coffeeMachineId) {
        log.info("startCoffeeMachine is calling..");
        startService.start(coffeeMachineId);
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new ModelAndView("forward:/coffee-machine/list");
    }

    @GetMapping("/{coffeeMachineId}/stop")
    public ModelAndView stopCoffeeMachine(
            @PathVariable UUID coffeeMachineId) {
        log.info("stopCoffeeMachine is calling..");
        stopService.stop(coffeeMachineId);
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new ModelAndView("forward:/coffee-machine/list");
    }

    @GetMapping("/{coffeeMachineId}/delete")
    public ModelAndView deleteCoffeeMachine(
            @PathVariable UUID coffeeMachineId) {
        log.info("deleteCoffeeMachine is calling..");
        deleteService.delete(coffeeMachineId);
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new ModelAndView("forward:/coffee-machine/list");
    }
}
