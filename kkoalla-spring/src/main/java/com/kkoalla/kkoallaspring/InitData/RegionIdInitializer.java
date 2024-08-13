package com.kkoalla.kkoallaspring.InitData;

import com.kkoalla.kkoallaspring.entity.RegionId;
import com.kkoalla.kkoallaspring.repository.RegionIdRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RegionIdInitializer {
    private final RegionIdRepository regionIdRepository;

    @PostConstruct
    public void init() {
        // 각 지역에 대한 RegionId가 데이터베이스에 있는지 확인하고, 없으면 삽입
        saveRegionIfNotExist(1L, "서울");
        saveRegionIfNotExist(2L, "강원도");
        saveRegionIfNotExist(3L, "경기도");
        saveRegionIfNotExist(4L, "부산");
        saveRegionIfNotExist(5L, "전남");
        saveRegionIfNotExist(6L, "충청북도");
        saveRegionIfNotExist(7L, "경상남도");
        saveRegionIfNotExist(8L, "경북");
        saveRegionIfNotExist(9L, "충남");
        saveRegionIfNotExist(10L, "제주도");
        saveRegionIfNotExist(11L, "전북");
        saveRegionIfNotExist(12L,"경상북도");
        saveRegionIfNotExist(13L,"경기");
        saveRegionIfNotExist(14L,"제주");
        saveRegionIfNotExist(15L,"충북");
        saveRegionIfNotExist(16L,"전라남도");
    }

    private void saveRegionIfNotExist(Long id, String name) {
        if (!regionIdRepository.existsById(id)) {
            RegionId regionId = new RegionId(id,name);
            regionIdRepository.save(regionId);
        }
    }
}
