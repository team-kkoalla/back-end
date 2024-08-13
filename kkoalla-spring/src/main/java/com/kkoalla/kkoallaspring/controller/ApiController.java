package com.kkoalla.kkoallaspring.controller;

import com.kkoalla.kkoallaspring.entity.BreweryInfo;
import com.kkoalla.kkoallaspring.entity.RegionId;
import com.kkoalla.kkoallaspring.repository.BreweryInfoRepository;
import com.kkoalla.kkoallaspring.repository.RegionIdRepository;
import com.kkoalla.kkoallaspring.service.ApiService;
import com.kkoalla.kkoallaspring.service.RegionMappingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ApiController {

    private final BreweryInfoRepository breweryInfoRepository;
    private final RegionIdRepository regionIdRepository;
    private final ApiService apiService;
    private final RegionMappingService regionMappingService;

    @GetMapping("/api")
    public String callApi() {
        try {
            // ApiService를 통해 데이터 가져오기
            JSONObject jsonObject = apiService.fetchData(1, 100);

            // data 배열을 받아옴
            JSONArray dataArray = jsonObject.getJSONArray("data");

            // 데이터 처리 반복문
            for (int i = 0; i < dataArray.length(); i++) {
                JSONObject tmp = dataArray.getJSONObject(i);

                // 각 필드 추출
                String programName = tmp.optString("체험프로그램명", "No Program Name");
                String breweryName = tmp.optString("양조장명", "No Brewery Name");
                String region = tmp.optString("양조장주소", "No Brewery Address");
                String content = tmp.optString("내용", "No Content");
                String duration = tmp.optString("소요시간", "No Duration");
                String contactNumber = tmp.optString("연락처", "No Contact Number");
                String reservationAvailable = tmp.optString("예약방문가능여부", "No Reservation Info");
                String location = tmp.optString("장소", "No Location");
                String alcoholType = tmp.optString("주종", "No Alcohol Type");
                int tourCost = tmp.optInt("투어비용(원)", 0);
                String website = tmp.optString("홈페이지", "No Website");

                // 주소에서 지역 이름 추출
                String regionPrefix = region.split(" ")[0];

                // RegionMappingService를 통해 지역 이름을 RegionId로 변환
                Long regionIdValue = regionMappingService.getRegionId(regionPrefix);
                if (regionIdValue == null) {
                    throw new RuntimeException("No matching RegionId for region: " + regionPrefix);
                }

                // 해당 RegionId를 DB에서 찾음
                RegionId regionId = regionIdRepository.findById(regionIdValue)
                        .orElseThrow(() -> new RuntimeException("RegionId not found for id: " + regionIdValue));
                log.info("Setting regionId: " + regionId.getId() + " for BreweryInfo: " + breweryName);

                // 엔티티 생성 및 데이터베이스에 저장
                BreweryInfo breweryInfo = new BreweryInfo(
                        programName,
                        breweryName,
                        region,
                        content,
                        duration,
                        contactNumber,
                        reservationAvailable,
                        location,
                        alcoholType,
                        tourCost,
                        website,
                        regionId
                );

                breweryInfoRepository.save(breweryInfo);
            }

        } catch (Exception e) {
            e.printStackTrace();
            return "API Call Failed";
        }
        return "API Call Completed";
    }
}
