package com.kkoalla.kkoallaspring.controller;

import com.kkoalla.kkoallaspring.dto.response.ProgramInfoDTO;
import com.kkoalla.kkoallaspring.repository.BreweryInfoRepository;
import com.kkoalla.kkoallaspring.service.BreweryInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/brewery")
public class BreweryListController {

    private final BreweryInfoService breweryInfoService;

    @Autowired
    public BreweryListController (BreweryInfoService breweryInfoService){
        this.breweryInfoService =breweryInfoService;
    }

    @GetMapping("/programs")
    public ResponseEntity<List<ProgramInfoDTO>> getBreweryPrograms() {
        List<ProgramInfoDTO> programs = breweryInfoService.findByBreweryName();
        return ResponseEntity.ok(programs);
    }
    @GetMapping("{regionId}/programs")
    public ResponseEntity<List<ProgramInfoDTO>> getProgramsByBreweryId(@PathVariable("regionId") Long regionId) {
        List<ProgramInfoDTO> programs = breweryInfoService.findProgramsByBreweryId(regionId);
        return ResponseEntity.ok(programs);
    }

}
