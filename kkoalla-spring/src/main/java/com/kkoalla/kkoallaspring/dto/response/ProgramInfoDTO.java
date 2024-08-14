package com.kkoalla.kkoallaspring.dto.response;

import com.kkoalla.kkoallaspring.entity.RegionId;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class ProgramInfoDTO {
    private String programName;
    private String breweryName;
    private String region;
    private Long regionId;




    public ProgramInfoDTO(String programName, String breweryName, String region,Long regionId){
        this.programName = programName;
        this.breweryName = breweryName;
        this.region = region;
        this.regionId =regionId;


    }
}
