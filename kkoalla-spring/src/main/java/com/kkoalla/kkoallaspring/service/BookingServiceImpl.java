package com.kkoalla.kkoallaspring.service;

import com.kkoalla.kkoallaspring.dto.request.BookingCountNumberDto;
import com.kkoalla.kkoallaspring.dto.request.BookingDto;
import com.kkoalla.kkoallaspring.entity.Booking;
import com.kkoalla.kkoallaspring.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookingServiceImpl implements BookingService {
    @Autowired
    private BookingRepository bookingRepository;

    public Booking ChangeEntity(BookingDto bookingDto){
        return new Booking(
                bookingDto.getUserName(),
                null, // phone은 DTO에 없으므로 null로 처리
                bookingDto.getBookingDate(),
                bookingDto.getPeopleCount(),
                bookingDto.getProgramTime(),
                null,
                null, // status는 DTO에 없으므로 null로 처리
                null, // user는 DTO에 없으므로 null로 처리
                null  // breweryInfo는 DTO에 없으므로 null로 처리
        );
    }

    public Booking getBookingCountNumber(BookingCountNumberDto bookingCountNumberDto){
        return new Booking(
                null,
                bookingCountNumberDto.getPhone(),
                null,
                0,
                null,
                bookingCountNumberDto.getCountNumber(),
                null,
                null,
                null
        );
    }

    @Override
    public void createBooking(BookingDto bookingDto) {
        // BookingDto를 이용해 Booking 엔티티 생성
        Booking booking = ChangeEntity(bookingDto);

        // 데이터베이스에 예약 저장
        bookingRepository.save(booking);
    }
    @Override
    public void createBookingCountNumber(BookingCountNumberDto bookingCountNumberDto) {
        Booking booking = getBookingCountNumber(bookingCountNumberDto);

        bookingRepository.save(booking);
    }
}
