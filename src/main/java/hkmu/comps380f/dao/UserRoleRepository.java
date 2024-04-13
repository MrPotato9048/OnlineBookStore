package hkmu.comps380f.dao;

import hkmu.comps380f.model.AppUser;
import hkmu.comps380f.model.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRoleRepository extends JpaRepository<AppUser, String> {
    @Query("SELECT ur FROM UserRole ur WHERE ur.username = :username AND ur.role = :role")
    Optional<UserRole> findByUsernameAndRole(@Param("username") String username, @Param("role") String role);
}
