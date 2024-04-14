package hkmu.comps380f.dao;

import hkmu.comps380f.model.AppUser;
import hkmu.comps380f.model.UserRole;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import jakarta.transaction.Transactional;

import org.eclipse.tags.shaded.java_cup.runtime.lr_parser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserManagementService {
    @Resource
    private AppUserRepository appUserRepo;
    @Resource
    private UserRoleRepository userRoleRepo;
    @Autowired
    private PasswordEncoder pe;

    @Transactional
    public void createAppUser(String username, String password, String[] roles, String fullName, String emailAddress, String deliveryAddress) {
        AppUser user = new AppUser(username, pe.encode(password), roles, fullName, emailAddress, deliveryAddress);
        appUserRepo.save(user);
    }

    @Transactional
    public List<AppUser> getAppUsers() {
        return appUserRepo.findAll();
    }

    @Transactional
    public AppUser getAppUser(String username) throws UsernameNotFoundException{
        AppUser appUser = appUserRepo.findById(username).orElse(null);
        if (appUser == null) {
            throw new UsernameNotFoundException("User " + username + " not found.");
        }
        return appUser;
    }

    @Transactional
    public void updateAppUser(String username, String newPassword, String[] roles, String fullName, String emailAddress, String deliveryAddress) {
        AppUser user = getAppUser(username);
        user.setPassword(pe.encode(newPassword));
        user.setFullName(fullName);
        user.setEmailAddress(emailAddress);
        user.setDeliveryAddress(deliveryAddress);

        List<UserRole> selectedRoles = Arrays.stream(roles).map(role -> getUserRole(username, role)).collect(Collectors.toList());

        user.getRoles().removeIf(role -> !selectedRoles.contains(role));
        selectedRoles.stream().filter(role -> !user.getRoles().contains(role)).forEach(user.getRoles()::add);

        appUserRepo.save(user);
    }

    @Transactional
    public void delete(String username) {
        AppUser appUser = appUserRepo.findById(username).orElse(null);
        if (appUser == null) {
            throw new UsernameNotFoundException("User " + username + " not found.");
        }
        appUserRepo.delete(appUser);
    }

    @Transactional
    @PostConstruct
    public void init() {
        if (appUserRepo.count() == 0) {
            AppUser userAdmin = new AppUser("admin", pe.encode("adminpw"), new String[]{"ROLE_ADMIN", "ROLE_USER"}, "Admin", "admin@email.com", "admin address");
            AppUser userNormal = new AppUser("user1", pe.encode("user1pw"), new String[]{"ROLE_USER"}, "User1", "user1@email.com", "user1 address");
            appUserRepo.save(userAdmin);
            appUserRepo.save(userNormal);
        }
    }

    public UserRole getUserRole(String username, String role) {
        return userRoleRepo.findByUsernameAndRole(username, role)
                .orElseThrow(() -> new RuntimeException("UserRole not found"));
    }
}
