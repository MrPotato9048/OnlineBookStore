package hkmu.comps380f.dao;

import hkmu.comps380f.model.AppUser;
import hkmu.comps380f.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AppUserRepository extends JpaRepository<AppUser, String> {
    AppUser findByUsername(String username);
}
