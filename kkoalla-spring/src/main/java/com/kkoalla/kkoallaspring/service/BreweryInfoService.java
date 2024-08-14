package com.kkoalla.kkoallaspring.service;

import com.kkoalla.kkoallaspring.dto.response.ProgramInfoDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BreweryInfoService {
    List<ProgramInfoDTO> findByBreweryName();
    List<ProgramInfoDTO> findProgramsByBreweryId(Long regionId);
}
