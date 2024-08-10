package cl.fullstackjava.controlreclamos_transporte.controller;

import cl.fullstackjava.controlreclamos_transporte.model.entities.User;
import cl.fullstackjava.controlreclamos_transporte.model.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute User user, RedirectAttributes redirectAttributes) {
        try {
            boolean success = userService.create(user);
            if (success) {
                redirectAttributes.addFlashAttribute("successMessage", "Registration successful! Please log in.");
                return "redirect:/login";
            } else {
                redirectAttributes.addFlashAttribute("errorMessage", "Registration failed. Please try again.");
                return "redirect:/register";
            }
        } catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
            return "redirect:/register";
        }
    }
}
