package by.pvt.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.logging.Logger;

@Controller
public class LoginController {
    private static Logger log = Logger.getLogger(LoginController.class.getName());

    @GetMapping(value = "/login")
    public ModelAndView signInPage(
            @RequestParam(value = "error", required = false) String error,
            @RequestParam(value = "logout", required = false) String logout) {
        ModelAndView modelAndView = new ModelAndView();
        //Initially when you hit on login url then error and logout both null
        if (error != null) {
            modelAndView.addObject("error", "Invalid username or password!");
        }

        if (logout != null) {
            modelAndView.addObject("msg", "You've been logged out successfully.");
        }
        modelAndView.setViewName("login");
        return modelAndView;
    }
}
