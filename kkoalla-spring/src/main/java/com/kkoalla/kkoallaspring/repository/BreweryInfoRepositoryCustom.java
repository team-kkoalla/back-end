package com.kkoalla.kkoallaspring.repository;

import com.kkoalla.kkoallaspring.entity.BreweryInfo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BreweryInfoRepositoryCustom {
    List<BreweryInfo> findByBreweryName();
    List<BreweryInfo> findProgramsByBreweryId(Long regionId);
    List<BreweryInfo> findAllBreweryInfo();
    List<BreweryInfo> findBreweryInfoByRegionIdAndBreweryInfoId(Long regionId,Long breweryInfoId);
    List<BreweryInfo> findProgramId(Long breweryId);
}
