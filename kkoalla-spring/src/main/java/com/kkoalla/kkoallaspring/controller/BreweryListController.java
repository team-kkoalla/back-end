package com.kkoalla.kkoallaspring.controller;

import ch.qos.logback.classic.Logger;
import com.kkoalla.kkoallaspring.dto.response.ProgramInfoDTO;
import com.kkoalla.kkoallaspring.repository.BreweryInfoRepository;
import com.kkoalla.kkoallaspring.service.ApiService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class BreweryListController {
//    private final BreweryInfoRepository breweryInfoRepository;
//
//    @GetMapping("/api/program")
//
//
//    public List<ProgramInfoDTO> getAllPrograms() {
//        try {
//            return breweryInfoRepository.findByBreweryName();
//        } catch (Exception e) {
//            log.error("Error occurred while fetching or processing data", e);
//            return List.of(); // 에러 발생 시 빈 리스트 반환
//        }
//    }



}
