package br.com.ftec.ecommerce.controller;

import br.com.ftec.ecommerce.model.User;
import br.com.ftec.ecommerce.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class LoginController {
    @Autowired
    private UserService userService;

    private static final Logger log = LoggerFactory.getLogger(LoginController.class);

    @GetMapping("/login")
    public String loginForm(Model model) {
        model.addAttribute("user", new User());
        return "login";
    }

    @GetMapping("/register")
    public String RegisterForm(Model model) {
        User user = new User();
        model.addAttribute(user);
        return "register";
    }

    @PostMapping("/register")
    public String RegisterForm(@ModelAttribute User user, @RequestParam(name = "role") String role) {
        User userExists = userService.findUserByEmail(user.getEmail());
        if (userExists != null) {
            return "login";
        } else {
            userService.saveUser(user, role);
            return "login";
        }
    }
}
