package com.kkoalla.kkoallaspring.service;

import com.kkoalla.kkoallaspring.dto.response.BreweryInfoDTO;
import com.kkoalla.kkoallaspring.dto.response.ProgramInfoDTO;
import com.kkoalla.kkoallaspring.entity.BreweryInfo;
import com.kkoalla.kkoallaspring.repository.BreweryInfoRepositoryCustom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BreweryInfoServiceImpl implements BreweryInfoService{

    private final BreweryInfoRepositoryCustom breweryInfoRepository;

    @Autowired
    public BreweryInfoServiceImpl(BreweryInfoRepositoryCustom breweryInfoRepository) {
        this.breweryInfoRepository = breweryInfoRepository;
    }

    @Override
    public List<ProgramInfoDTO> findByBreweryName() {
        List<BreweryInfo> breweryInfos = breweryInfoRepository.findByBreweryName();
        return breweryInfos.stream()
                .map(ProgramInfoDTO::new)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProgramInfoDTO> findProgramsByBreweryId(Long regionId) {
        List<BreweryInfo> breweryInfos = breweryInfoRepository.findProgramsByBreweryId(regionId);
        return breweryInfos.stream()
                .map(ProgramInfoDTO::new)
                .collect(Collectors.toList());
    }

    @Override
    public List<BreweryInfoDTO> findAllBreweryInfoDTOs() {
        List<BreweryInfo> breweryInfos = breweryInfoRepository.findAllBreweryInfo();
        return breweryInfos.stream()
                .map(BreweryInfoDTO::new) // 간결하게 DTO로 변환
                .collect(Collectors.toList());
    }

    @Override
    public List<BreweryInfoDTO> findBreweryInfoDetailByRegionIdAndBreweryInfoId(Long regionId, Long breweryInfoId) {
        List<BreweryInfo> breweryInfos = breweryInfoRepository.findBreweryInfoByRegionIdAndBreweryInfoId(regionId, breweryInfoId);
        return breweryInfos.stream()
                .map(BreweryInfoDTO::new) // BreweryInfo를 BreweryInfoDTO로 변환
                .collect(Collectors.toList());
    }

}
