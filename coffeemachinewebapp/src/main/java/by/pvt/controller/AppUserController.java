package by.pvt.controller;

import by.pvt.service.user.register.AppUserRegisterCmd;
import by.pvt.service.user.register.AppUserRegisterService;
import by.pvt.service.user.register.AppUserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/registration")
public class AppUserController {

    @Autowired
    AppUserRegisterService appUserRegisterService;

    @Autowired
    AppUserValidator appUserValidator;

    @GetMapping
    public ModelAndView showRegistrationForm() {
        ModelAndView view = new ModelAndView("registration", "user", new AppUserRegisterCmd());
        return view;
    }

    @PostMapping
    public String registrationForm(
            @Validated @ModelAttribute("user") AppUserRegisterCmd appUserRegisterCmd,
            BindingResult result
    ) {
        appUserValidator.validate(appUserRegisterCmd, result);
        if (result.hasErrors()) {
            return "registration";
        }

        if (appUserRegisterService.saveUser(appUserRegisterCmd)) {
            return "redirect:/registration/registration-success";
        } else {
            return "registrationError";
        }
    }

    @GetMapping("/registration-success")
    public String showRegistrationSuccessPage() {
        return "registrationSuccess";
    }

}
