package com.kkoalla.kkoallaspring.repository;

import com.kkoalla.kkoallaspring.entity.RegionId;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface RegionIdRepository extends JpaRepository<RegionId,Long> {


}
