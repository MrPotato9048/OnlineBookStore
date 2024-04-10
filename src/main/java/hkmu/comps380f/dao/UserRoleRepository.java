package hkmu.comps380f.dao;

import hkmu.comps380f.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRoleRepository extends JpaRepository<AppUser, String> {
}
