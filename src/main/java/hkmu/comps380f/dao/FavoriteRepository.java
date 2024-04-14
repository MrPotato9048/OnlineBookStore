package hkmu.comps380f.dao;

import hkmu.comps380f.model.AppUser;
import hkmu.comps380f.model.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FavoriteRepository extends JpaRepository<Favorite, Long> {
        List<Favorite> findByAppUser(AppUser appUser);
        Favorite findByAppUserAndBookId(AppUser appUser, Long bookId);
}
