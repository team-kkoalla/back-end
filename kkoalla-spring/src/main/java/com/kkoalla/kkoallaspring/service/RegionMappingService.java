package com.kkoalla.kkoallaspring.service;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class RegionMappingService {

    private final Map<String, Long> regionMap = new HashMap<>();

    public RegionMappingService() {
        // 각 도 및 광역시에 대한 코드 매핑
        regionMap.put("서울", 1L);
        regionMap.put("강원도", 2L);
        regionMap.put("경기도", 3L);
        regionMap.put("부산", 4L);
        regionMap.put("전남", 5L);
        regionMap.put("충청북도", 6L);
        regionMap.put("경상남도", 7L);
        regionMap.put("경북", 8L);
        regionMap.put("충남", 9L);
        regionMap.put("제주도", 10L);
        regionMap.put("전북", 11L);
        regionMap.put("경상북도",12L);
        regionMap.put("경기",13L);
        regionMap.put("제주",14L);
        regionMap.put("충북",15L);
        regionMap.put("전라남도",16L);
    }

    public Long getRegionId(String regionName) {
        return regionMap.get(regionName);
    }
}
