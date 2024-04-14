package hkmu.comps380f.dao;

import hkmu.comps380f.model.CheckoutModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CheckoutRepository extends JpaRepository<CheckoutModel, Long> {
}
