package hkmu.comps380f.dao;

import hkmu.comps380f.model.Cover;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CoverRepository extends JpaRepository<Cover, UUID> {
}
