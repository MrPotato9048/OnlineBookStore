package hkmu.comps380f.dao;

import hkmu.comps380f.model.AppUser;
import hkmu.comps380f.model.Order;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findByAppUser(AppUser appUser);

}
