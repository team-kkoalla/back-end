package com.kkoalla.kkoallaspring.entity;

import com.kkoalla.kkoallaspring.entity.base.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Booking extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "booking_id")
    private Long id;

    @Column(name = "user_name")
    private String userName;
    private String phone;
    private String bookingDate;
    private int peopleCount;
    private String programTime;
    private String countNumber;

    @Enumerated(EnumType.STRING)
    private BookingStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "brewery_info_id")
    private BreweryInfo breweryInfo;

    public Booking(String userName, String phone, String bookingDate, int peopleCount, String programTime,String countNumber, BookingStatus status, User user, BreweryInfo breweryInfo) {
        this.userName = userName;
        this.phone = phone;
        this.bookingDate = bookingDate;
        this.peopleCount = peopleCount;
        this.programTime= programTime;
        this.countNumber = countNumber;
        this.status = status;
        this.user = user;
        this.breweryInfo = breweryInfo ;
    }
}
