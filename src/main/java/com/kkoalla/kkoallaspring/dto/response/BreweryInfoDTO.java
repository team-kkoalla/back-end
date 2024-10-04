package com.kkoalla.kkoallaspring.dto.response;

import lombok.Data;

@Data
public class BreweryInfoDTO {


    private  String programName;
    private  String breweryName;
    private  String breweryAddress;
    private  String content;
    private  String duration;
    private  String contactNumber;
    private  String reservationAvailable;
    private  String location;
    private  String alcoholType;
    private  Integer tourCost;
    private  String website;

    public BreweryInfoDTO(String programName, String breweryName, String breweryAddress, String content, String duration, String contactNumber, String reservationAvailable, String location, String alcoholType, Integer tourCost, String website) {
        this.programName = programName;
        this.breweryName = breweryName;
        this.breweryAddress = breweryAddress;
        this.content = content;
        this.duration = duration;
        this.contactNumber = contactNumber;
        this.reservationAvailable = reservationAvailable;
        this.location = location;
        this.alcoholType = alcoholType;
        this.tourCost = tourCost;
        this.website = website;
    }
}
