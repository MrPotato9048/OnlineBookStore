package hkmu.comps380f.dao;

import hkmu.comps380f.model.AppUser;
import hkmu.comps380f.model.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Long> {
    ShoppingCart findByAppUser(AppUser appUser);
}
