package com.kkoalla.kkoallaspring.dto.request;

import com.kkoalla.kkoallaspring.entity.Booking;
import lombok.Data;

@Data
public class BookingCountNumberDto {
    private String Phone;
    private String countNumber;

    public BookingCountNumberDto(Booking booking) {
        this.Phone = booking.getPhone();
        this.countNumber = booking.getCountNumber();
    }

    public BookingCountNumberDto(){
    }
}
