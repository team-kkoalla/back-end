package com.kkoalla.kkoallaspring.controller;


import com.kkoalla.kkoallaspring.dto.response.BreweryInfoDTO;
import com.kkoalla.kkoallaspring.entity.BreweryInfo;
import com.kkoalla.kkoallaspring.repository.BreweryInfoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ApiController {

    private final BreweryInfoRepository breweryInfoRepository;

    @GetMapping("/api")
    public String callApi() {
        String result = "";
        try {
            String urlStr = "https://api.odcloud.kr/api/15089109/v1/uddi:c7468573-84ff-4a92-a84b-884439ce23d3?" +
                    "serviceKey=pRf%2B4%2FO3yi5ghMiMnogtjJ%2BeywrxfWIHFuFim%2F5w002UPEh6mv1qz6iidgZK6l9ZmHVBKisH8XgFpAkIaSd3qw%3D%3D" +
                    "&page=1&perPage=100" +
                    "&returnType=JSON";
            URL url = new URL(urlStr);
            BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));

            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            result = sb.toString();

            // JSON 파싱
            JSONObject jsonObject = new JSONObject(result);

            // data 배열을 받아옴
            JSONArray dataArray = jsonObject.getJSONArray("data");

            // 데이터 처리 반복문
            for (int i = 0; i < dataArray.length(); i++) {
                JSONObject tmp = dataArray.getJSONObject(i);

                // 각 필드 추출
                String programName = tmp.optString("체험프로그램명", "No Program Name");
                String breweryName = tmp.optString("양조장명", "No Brewery Name");
                String breweryAddress = tmp.optString("양조장주소", "No Brewery Address");
                String content = tmp.optString("내용", "No Content");
                String duration = tmp.optString("소요시간", "No Duration");
                String contactNumber = tmp.optString("연락처", "No Contact Number");
                String reservationAvailable = tmp.optString("예약방문가능여부", "No Reservation Info");
                String location = tmp.optString("장소", "No Location");
                String alcoholType = tmp.optString("주종", "No Alcohol Type");
                int tourCost = tmp.optInt("투어비용(원)", 0);
                String website = tmp.optString("홈페이지", "No Website");

                // BreweryInfoDTO 객체 생성
                BreweryInfoDTO breweryInfoDTO = new BreweryInfoDTO(
                        programName,
                        breweryName,
                        breweryAddress,
                        content,
                        duration,
                        contactNumber,
                        reservationAvailable,
                        location,
                        alcoholType,
                        tourCost,
                        website
                );

                // DTO를 엔티티로 변환
                BreweryInfo breweryInfo = new BreweryInfo(
                        breweryInfoDTO.getProgramName(),
                        breweryInfoDTO.getBreweryName(),
                        breweryInfoDTO.getBreweryAddress(),
                        breweryInfoDTO.getContent(),
                        breweryInfoDTO.getDuration(),
                        breweryInfoDTO.getContactNumber(),
                        breweryInfoDTO.getReservationAvailable(),
                        breweryInfoDTO.getLocation(),
                        breweryInfoDTO.getAlcoholType(),
                        breweryInfoDTO.getTourCost(),
                        breweryInfoDTO.getWebsite()
                );

                // 데이터베이스에 저장
                breweryInfoRepository.save(breweryInfo);
            }

        } catch (Exception e) {
            e.printStackTrace();
            return "API Call Failed";
        }
        return "API Call Completed";
    }
}
