package com.kkoalla.kkoallaspring.service;

import com.kkoalla.kkoallaspring.dto.request.BookingCountNumberDto;
import com.kkoalla.kkoallaspring.dto.request.BookingDto;
import org.springframework.stereotype.Service;

@Service
public interface BookingService {
    void createBooking(BookingDto bookingDto);
    void createBookingCountNumber(BookingCountNumberDto bookingCountNumberDto);
}
