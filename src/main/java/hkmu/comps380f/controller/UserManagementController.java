package hkmu.comps380f.controller;

import hkmu.comps380f.dao.UserManagementService;
import hkmu.comps380f.validator.UserValidator;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

@Controller
@RequestMapping("/user")
public class UserManagementController {
    @Resource
    private UserManagementService umService;

    @GetMapping("/create")
    public ModelAndView create() {
        return new ModelAndView("addUser", "appUser", new UserForm());
    }

    public static class UserForm {
        @NotEmpty(message = "Please enter your user name.")
        private String username;
        @NotEmpty(message = "Please enter your password.")
        @Size(min = 6, max = 15, message = "Your password length must be between {min} and {max}.")
        private String password;
        private String confirmPassword;
        @NotEmpty(message = "Please select at least one role.")
        private String[] roles;

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

        public String[] getRoles() {
            return roles;
        }

        public void setRoles(String[] roles) {
            this.roles = roles;
        }

        public String getConfirmPassword() {
            return confirmPassword;
        }

        public void setConfirmPassword(String confirmPassword) {
            this.confirmPassword = confirmPassword;
        }
    }

    @Autowired
    private UserValidator userValidator;

    @PostMapping("/create")
    public String create(@ModelAttribute("appUser") @Valid UserForm form, BindingResult result) throws IOException {
        userValidator.validate(form, result);

        if (result.hasErrors()) {
            return "addUser";
        }

        umService.createAppUser(form.getUsername(), form.getPassword(), form.getRoles());
        return "redirect:/user/list";
    }
    @GetMapping({"", "/", "/list"})
    public String list(ModelMap model) {
        model.addAttribute("appUsers", umService.getAppUsers());
        return "listUser";
    }
    @GetMapping("/delete/{username}")
    public String deleteAppUser(@PathVariable("username") String username) {
        umService.delete(username);
        return "redirect:/user/list";
    }
}
