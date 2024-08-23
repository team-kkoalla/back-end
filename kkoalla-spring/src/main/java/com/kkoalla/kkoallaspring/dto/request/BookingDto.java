package com.kkoalla.kkoallaspring.dto.request;

import com.kkoalla.kkoallaspring.entity.Booking;
import lombok.Data;

@Data
public class BookingDto {

    private String userName;
    private String bookingDate;
    private int peopleCount;
    private String programTime;

    public BookingDto (Booking booking){
        this.userName = booking.getUserName();
        this.programTime =booking.getProgramTime();
        this.bookingDate= booking.getBookingDate();
        this.peopleCount = booking.getPeopleCount();
    }

    // 기본 생성자 (필수)
    public BookingDto() {}
}
