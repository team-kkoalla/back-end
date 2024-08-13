package com.kkoalla.kkoallaspring.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.kkoalla.kkoallaspring.entity.base.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "brewery_info")
@Getter
public class BreweryInfo extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "brewery_info_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "region_id")
    private RegionId regionId ;

    @JsonProperty("체험프로그램명")
    private String programName;

    @JsonProperty("양조장명")
    private String breweryName;

    @JsonProperty("양조장주소")

    private String region;

    @JsonProperty("내용")
    private String content;

    @JsonProperty("소요시간")
    private String duration;

    @JsonProperty("연락처")
    private String contactNumber;

    @JsonProperty("예약방문가능여부")
    private String reservationAvailable;

    @JsonProperty("장소")
    private String location;

    @JsonProperty("주종")
    private String alcoholType;

    @JsonProperty("투어비용(원)")
    private Integer tourCost;

    @JsonProperty("홈페이지")
    private String website;

    public BreweryInfo(String programName, String breweryName, String region, String content, String duration,
                       String contactNumber, String reservationAvailable, String location, String alcoholType,
                       Integer tourCost, String website, RegionId regionId) {
        this.programName = programName;
        this.breweryName = breweryName;
        this.region = region;
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
