package hkmu.comps380f.validator;
import hkmu.comps380f.controller.UserManagementController.UserForm;
import hkmu.comps380f.dao.AppUserRepository;
import hkmu.comps380f.model.AppUser;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class UserValidator implements Validator {
    @Resource
    AppUserRepository appUserRepo;
    @Override
    public boolean supports(Class<?> type) {
        return UserForm.class.equals(type);
    }
    @Override
    public void validate(Object o, Errors errors) {
        UserForm user = (UserForm) o;
        ValidationUtils.rejectIfEmpty(errors, "confirmPassword", "",
                "Please confirm your password.");
        if (!user.getPassword().equals(user.getConfirmPassword())) {
            errors.rejectValue("confirmPassword", "", "Password does not match.");
        }
        if (user.getUsername().equals("")) {
            return;
        }
        AppUser appUser = appUserRepo.findById(user.getUsername()).orElse(null);
        if (appUser != null) {
            errors.rejectValue("username", "" , "User already exists.");
        }
    }
}