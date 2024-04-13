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

    public static class UserForm {
        private String username;
        private String password;
        private String role;
        private String fullName;
        private String emailAddress;
        private String deliveryAddress;

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getRole() {return role;}

        public void setRole(String role) {this.role = role;}

        public String getFullName() {
            return fullName;
        }

        public void setFullName(String fullName) {
            this.fullName = fullName;
        }

        public String getEmailAddress() {
            return emailAddress;
        }

        public void setEmailAddress(String emailAddress) {
            this.emailAddress = emailAddress;
        }

        public String getDeliveryAddress() {
            return deliveryAddress;
        }

        public void setDeliveryAddress(String deliveryAddress) {
            this.deliveryAddress = deliveryAddress;
        }
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
