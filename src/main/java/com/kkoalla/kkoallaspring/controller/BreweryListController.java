package com.kkoalla.kkoallaspring.controller;

import ch.qos.logback.classic.Logger;
import com.kkoalla.kkoallaspring.dto.response.ProgramInfoDTO;
import com.kkoalla.kkoallaspring.service.ApiService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor

public class BreweryListController {
    private ApiService apiService;
    private Logger log;

    @GetMapping("/api/breweryList")
    public List<ProgramInfoDTO> getProgramInfo() {
        List<ProgramInfoDTO> programInfoList = new ArrayList<>();
        try {
            JSONArray dataArray = apiService.fetchData(1, 100);

            for (int i = 0; i < dataArray.length(); i++) {
                JSONObject tmp = dataArray.getJSONObject(i);

                // 필요한 필드만 추출
                String programName = tmp.optString("체험프로그램명", "No Program Name");
                String breweryName = tmp.optString("양조장명", "No Brewery Name");
                String breweryAddress = tmp.optString("양조장주소", "No Brewery Address");

                // DTO 생성 및 리스트에 추가
                ProgramInfoDTO programInfoDTO = new ProgramInfoDTO(programName, breweryName, breweryAddress);
                programInfoList.add(programInfoDTO);
            }

        } catch (Exception e) {
            log.error("Error occurred while fetching or processing data", e);
        }

        return programInfoList;
    }


}
