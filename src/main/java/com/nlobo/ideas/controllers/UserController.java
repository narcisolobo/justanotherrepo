package com.nlobo.ideas.controllers;

import com.nlobo.ideas.models.User;
import com.nlobo.ideas.services.UserService;
import com.nlobo.ideas.validators.UserValidator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class UserController {
    private final UserService userService;
    private final UserValidator userValidator;
    public UserController(UserService userService, UserValidator userValidator) {
        this.userService = userService;
        this.userValidator = userValidator;
    }

    @RequestMapping("/")
    public String index() { return "redirect:/login"; }

    @RequestMapping(value = "/login")
    public String loginForm() { return "login.jsp"; }

    @RequestMapping(value = "/register")
    public String registerForm(Model model) {
        model.addAttribute("user", new User());
        return "register.jsp";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(
            @RequestParam("email") String email,
            @RequestParam("password") String password,
            HttpSession session,
            RedirectAttributes redirectAttributes) {
        if (userService.authenticateUser(email, password)) {
            User user = userService.findByEmail(email);
            session.setAttribute("userId", user.getId());
            return "redirect:/ideas";
        } else {
            redirectAttributes.addFlashAttribute("error", "Invalid login credentials.");
            return "redirect:/login";
        }
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(
            @Valid @ModelAttribute("user") User user, BindingResult result,
            HttpSession session) {
        userValidator.validate(user, result);

        if (result.hasErrors()) {
            return "register.jsp";
        } else {
            User registeredUser = userService.registerUser(user);
            session.setAttribute("userId", registeredUser.getId());
            return "redirect:/ideas";
        }
    }

    @RequestMapping(value = "/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}
