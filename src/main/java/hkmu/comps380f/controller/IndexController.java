package hkmu.comps380f.controller;

import hkmu.comps380f.dao.UserManagementService;
import hkmu.comps380f.validator.UserValidator;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

@Controller
public class IndexController {
    @Resource
    private UserManagementService umService;

    @GetMapping("/")
    public String index() {
        return "redirect:/book/list";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public ModelAndView register() {
        return new ModelAndView("userRegister", "appUser", new UserManagementController.UserForm());
    }

    @Autowired
    private UserValidator userValidator;
    @PostMapping("/register")
    public String register(@ModelAttribute("appUser") @Valid UserManagementController.UserForm form, BindingResult result) throws IOException {
        userValidator.validate(form, result);

        if (result.hasErrors()) {
            return "userRegister";
        }

        umService.createAppUser(form.getUsername(), form.getPassword(), form.getRoles(), form.getFullName(), form.getEmailAddress(), form.getDeliveryAddress());
        return "redirect:/login";
    }
}
