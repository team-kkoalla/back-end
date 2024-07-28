package com.kkoalla.kkoallaspring.entity;

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

    @Column(name = "program_name")
    private String programName;

    @Column(name = "program_explanation")
    private String programExplanation;

    private int min;
    private int max;

    private Float latitude;
    private Float longitude;
}
