package com.kkoalla.kkoallaspring.repository;

import com.kkoalla.kkoallaspring.dto.response.ProgramInfoDTO;
import com.kkoalla.kkoallaspring.entity.BreweryInfo;
import jakarta.persistence.EntityManager;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Repository
public interface BreweryInfoRepository extends JpaRepository<BreweryInfo,Long>,BreweryInfoRepositoryCustom {

}
