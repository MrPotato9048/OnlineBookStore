package hkmu.comps380f.dao;

import hkmu.comps380f.model.AppUser;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserManagementService {
    @Resource
    private AppUserRepository appUserRepo;
    @Autowired
    private PasswordEncoder pe;

    @Transactional
    public void createAppUser(String username, String password, String[] roles) {
        AppUser user = new AppUser(username, pe.encode(password), roles);
        appUserRepo.save(user);
    }
    @Transactional
    public List<AppUser> getAppUsers() {
        return appUserRepo.findAll();
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
            AppUser user = new AppUser("testing", pe.encode("testing"), new String[]{"ROLE_ADMIN", "ROLE_USER"});
            appUserRepo.save(user);
        }
    }
}
