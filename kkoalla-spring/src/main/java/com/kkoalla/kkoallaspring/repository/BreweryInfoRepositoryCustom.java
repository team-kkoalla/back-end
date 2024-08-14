package com.kkoalla.kkoallaspring.repository;

import com.kkoalla.kkoallaspring.dto.response.ProgramInfoDTO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BreweryInfoRepositoryCustom {
    List<ProgramInfoDTO> findByBreweryName();
    List<ProgramInfoDTO> findProgramsByBreweryId(Long regionId);
}
