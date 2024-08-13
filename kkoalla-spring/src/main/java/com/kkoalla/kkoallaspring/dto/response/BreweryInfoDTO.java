package com.kkoalla.kkoallaspring.dto.response;


import lombok.Data;

@Data
public class BreweryInfoDTO {


    private  String programName;
    private  String breweryName;
    private  String region;
    private  String content;
    private  String duration;
    private  String contactNumber;
    private  String reservationAvailable;
    private  String location;
    private  String alcoholType;
    private  Integer tourCost;
    private  String website;
    private Long regionId;

    public BreweryInfoDTO() {
        this.programName = programName;
        this.breweryName = breweryName;
        this.region= region;
        this.content = content;
        this.duration = duration;
        this.contactNumber = contactNumber;
        this.reservationAvailable = reservationAvailable;
        this.location = location;
        this.alcoholType = alcoholType;
        this.tourCost = tourCost;
        this.website = website;
        this.regionId =regionId;
    }
}
