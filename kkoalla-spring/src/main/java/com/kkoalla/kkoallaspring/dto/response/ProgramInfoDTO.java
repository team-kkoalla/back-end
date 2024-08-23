package com.kkoalla.kkoallaspring.dto.response;

import com.kkoalla.kkoallaspring.entity.BreweryInfo;
import com.kkoalla.kkoallaspring.entity.RegionId;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class ProgramInfoDTO {
    private Long id;
    private String programName;
    private String breweryName;
    private String region;
    private Long regionId;




    public ProgramInfoDTO(BreweryInfo breweryInfo){
        this.id=breweryInfo.getId();
        this.programName = breweryInfo.getProgramName();
        this.breweryName = breweryInfo.getBreweryName();
        this.region = breweryInfo.getRegion();
        this.regionId = breweryInfo.getRegionId().getId();

    }
}
