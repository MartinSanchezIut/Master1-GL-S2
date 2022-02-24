package fr.sanchez.gl.controller;

import fr.sanchez.gl.model.Registration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegistrationController {
    @GetMapping("register")
    public String getRegistration(@ModelAttribute("registration")Registration registration) {
        return "register";
    }

    @PostMapping("register")
    public String addRegistration(@ModelAttribute("registration")Registration registration) {
        // ...
        return "register";
    }
}

