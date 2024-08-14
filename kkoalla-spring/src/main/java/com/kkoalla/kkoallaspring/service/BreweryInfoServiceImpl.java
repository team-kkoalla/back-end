package com.kkoalla.kkoallaspring.service;

import com.kkoalla.kkoallaspring.dto.response.ProgramInfoDTO;
import com.kkoalla.kkoallaspring.repository.BreweryInfoRepositoryCustom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BreweryInfoServiceImpl implements BreweryInfoService{

    private final BreweryInfoRepositoryCustom breweryInfoRepository;

    @Autowired
    public BreweryInfoServiceImpl(BreweryInfoRepositoryCustom breweryInfoRepository) {
        this.breweryInfoRepository = breweryInfoRepository;
    }

    @Override
    public List<ProgramInfoDTO> findByBreweryName() {
        return breweryInfoRepository.findByBreweryName();
    }

    @Override
    public List<ProgramInfoDTO> findProgramsByBreweryId(Long regionId) {
        return breweryInfoRepository.findProgramsByBreweryId(regionId);
    }
}
