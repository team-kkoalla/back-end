package com.kkoalla.kkoallaspring.entity;

import com.kkoalla.kkoallaspring.entity.base.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Brewery extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "brewery_id")
    private Integer id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "brewery_info_id")
    private BreweryInfo breweryInfo;

    @OneToMany(mappedBy = "brewery", cascade = CascadeType.ALL, orphanRemoval = true)
    List<Booking> bookings = new ArrayList<>();

    public void addBooking(Booking booking) {
        bookings.add(booking);
        booking.setBrewery(this);
    }

    public void removeBooking(Booking booking) {
        bookings.remove(booking);
        booking.setBrewery(null);
    }
}
