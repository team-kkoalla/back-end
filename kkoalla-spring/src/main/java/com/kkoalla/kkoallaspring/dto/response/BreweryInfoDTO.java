package com.kkoalla.kkoallaspring.dto.response;


import com.kkoalla.kkoallaspring.entity.BreweryInfo;
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

    public BreweryInfoDTO(BreweryInfo breweryInfo) {
        this.programName = breweryInfo.getProgramName();
        this.breweryName = breweryInfo.getBreweryName();
        this.region = breweryInfo.getRegion();
        this.content = breweryInfo.getContent();
        this.duration = breweryInfo.getDuration();
        this.contactNumber = breweryInfo.getContactNumber();
        this.reservationAvailable = breweryInfo.getReservationAvailable();
        this.location = breweryInfo.getLocation();
        this.alcoholType = breweryInfo.getAlcoholType();
        this.tourCost = breweryInfo.getTourCost();
        this.website = breweryInfo.getWebsite();
        this.regionId = breweryInfo.getRegionId().getId();
    }
}
