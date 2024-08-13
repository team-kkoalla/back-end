package com.kkoalla.kkoallaspring.dto.response;


import lombok.Data;

@Data

public class RegionIdDTO {
    private Long id;
    private String region;


    public RegionIdDTO(Long id, String region){
        this.id = id;
        this.region = region;
    }

}
