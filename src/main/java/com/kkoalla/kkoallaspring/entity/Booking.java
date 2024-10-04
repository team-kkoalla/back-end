package com.kkoalla.kkoallaspring.entity;

import com.kkoalla.kkoallaspring.entity.base.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Data
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

    @Enumerated(EnumType.STRING)
    private BookingStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "brewery_id")
    private Brewery brewery;

    public Booking(String userName, String phone, String bookingDate, int peopleCount, BookingStatus status, User user, Brewery brewery) {
        this.userName = userName;
        this.phone = phone;
        this.bookingDate = bookingDate;
        this.peopleCount = peopleCount;
        this.status = status;
        this.user = user;
        this.brewery = brewery;
    }
}
