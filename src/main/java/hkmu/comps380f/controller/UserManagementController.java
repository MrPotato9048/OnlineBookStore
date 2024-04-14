package hkmu.comps380f.controller;

import hkmu.comps380f.dao.FavoriteBookService;
import hkmu.comps380f.dao.UserManagementService;
import hkmu.comps380f.model.AppUser;
import hkmu.comps380f.model.Favorite;
import hkmu.comps380f.model.UserRole;
import hkmu.comps380f.validator.UserValidator;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/user")
public class UserManagementController {
    @Resource
    private UserManagementService umService;

    @Resource
    private FavoriteBookService fbService;

    @Autowired
    private PasswordEncoder passwordEncoder;

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
        @NotEmpty(message = "Please enter your full name.")
        private String fullName;
        @NotEmpty(message = "Please enter your email address.")
        private String emailAddress;
        @NotEmpty(message = "Please enter your delivery address.")
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

        public String getFullName() { return fullName; }

        public void setFullName(String fullName) { this.fullName = fullName; }

        public String getEmailAddress() { return emailAddress; }

        public void setEmailAddress(String emailAddress) { this.emailAddress = emailAddress; }

        public String getDeliveryAddress() { return deliveryAddress; }

        public void setDeliveryAddress(String deliveryAddress) { this.deliveryAddress = deliveryAddress; }
    }

    public static class RegisterUserForm {
        @NotEmpty(message = "Please enter your user name.")
        private String username;
        @NotEmpty(message = "Please enter your current password.")
        private String currentPassword;
        @NotEmpty(message = "Please enter your new password.")
        @Size(min = 6, max = 15, message = "Your password length must be between {min} and {max}.")
        private String newPassword;
        private String confirmPassword;
        @NotEmpty(message = "Please select at least one role.")
        private String[] roles;
        @NotEmpty(message = "Please enter your full name.")
        private String fullName;
        @NotEmpty(message = "Please enter your email address.")
        private String emailAddress;
        @NotEmpty(message = "Please enter your delivery address.")
        private String deliveryAddress;

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getCurrentPassword() {
            return currentPassword;
        }

        public void setCurrentPassword(String currentPassword) {
            this.currentPassword = currentPassword;
        }

        public String getNewPassword() {
            return newPassword;
        }

        public void setNewPassword(String newPassword) {
            this.newPassword = newPassword;
        }

        public String getConfirmPassword() {
            return confirmPassword;
        }

        public void setConfirmPassword(String confirmPassword) {
            this.confirmPassword = confirmPassword;
        }

        public String[] getRoles() {
            return roles;
        }

        public void setRoles(String[] roles) {
            this.roles = roles;
        }

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

    @PostMapping("/create")
    public String create(@ModelAttribute("appUser") @Valid UserForm form, BindingResult result) throws IOException {
        userValidator.validate(form, result);

        if (result.hasErrors()) {
            return "addUser";
        }

        umService.createAppUser(form.getUsername(), form.getPassword(), form.getRoles(), form.getFullName(), form.getEmailAddress(), form.getDeliveryAddress());
        return "redirect:/user/list";
    }
    @GetMapping({"", "/", "/list"})
    public String list(Principal principal, ModelMap model) {
        model.addAttribute("appUsers", umService.getAppUsers());
        model.addAttribute("principal", principal);
        return "listUser";
    }
    @GetMapping("/delete/{username}")
    public String deleteAppUser(@PathVariable("username") String username) {
        umService.delete(username);
        return "redirect:/user/list";
    }

    @GetMapping("/own/{username}")
    public String showUser(@PathVariable("username") String username, Principal principal, ModelMap model) {
        AppUser appUser = umService.getAppUser(username);
        if (appUser == null) {
            return "redirect:/user/list";
        }
        List<Favorite> favoriteBooks = fbService.getFavoriteBooksByUsername(username);
        model.addAttribute("favoriteBooks", favoriteBooks);
        model.addAttribute("appUser", appUser);
        model.addAttribute("principal", principal);
        return "viewUser";
    }

    @GetMapping("/edit/{username}")
    public String editUser(@PathVariable("username") String username, Principal principal, ModelMap model) {
        AppUser appUser = umService.getAppUser(username);
        RegisterUserForm form = new RegisterUserForm();
        model.addAttribute("appUser", appUser);
        model.addAttribute("registerUserForm", form);
        model.addAttribute("principal", principal);
        return "editUser";
    }

    @PostMapping("/update")
    public String updateUser(@Valid RegisterUserForm form, BindingResult result, Principal principal) {
        System.out.println("Updating user...");
        // Fetch the current user
        AppUser currentUser = umService.getAppUser(form.getUsername());
        // Check if the current password is correct
        if (!passwordEncoder.matches(form.getCurrentPassword(), currentUser.getPassword())) {
            result.rejectValue("currentPassword", "error.currentPassword", "Current password is incorrect");
        }
        // Check if the new password and confirm password match
        if (!form.getNewPassword().equals(form.getConfirmPassword())) {
            result.rejectValue("confirmPassword", "error.confirmPassword", "Passwords do not match");
        }
        // Check if the new password is the same as the old password
        if (passwordEncoder.matches(form.getNewPassword(), currentUser.getPassword())) {
            result.rejectValue("newPassword", "error.newPassword", "New password is the same as the old password");
        }
        if (result.hasErrors()) {
            return "editUser";
        }
        List<UserRole> selectedRoles = Arrays.stream(form.getRoles()).map(role -> umService.getUserRole(currentUser.getUsername(), role)).collect(Collectors.toList());
        currentUser.getRoles().removeIf(role -> !selectedRoles.contains(role));
        selectedRoles.stream().filter(role -> !currentUser.getRoles().contains(role)).forEach(currentUser.getRoles()::add);
        String[] currentRolesArray = selectedRoles.stream().map(UserRole::getRole).toArray(String[]::new);
        umService.updateAppUser(form.getUsername(), form.getNewPassword(), currentRolesArray, form.getFullName(), form.getEmailAddress(), form.getDeliveryAddress());
        return "redirect:/user/own/" + principal.getName();
    }
}
