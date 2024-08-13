package com.kkoalla.kkoallaspring.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class ProgramInfoDTO {
    private String programName;
    private String breweryName;
    private String region;


    public ProgramInfoDTO(String programName, String breweryName, String region){
        this.programName = programName;
        this.breweryName = breweryName;
        this.region = region;
    }
}
