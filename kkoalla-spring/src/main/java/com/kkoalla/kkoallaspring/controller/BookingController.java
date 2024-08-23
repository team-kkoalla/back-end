package com.kkoalla.kkoallaspring.controller;


import com.kkoalla.kkoallaspring.dto.request.BookingCountNumberDto;
import com.kkoalla.kkoallaspring.dto.request.BookingDto;
import com.kkoalla.kkoallaspring.dto.response.ProgramInfoDTO;
import com.kkoalla.kkoallaspring.service.BookingService;
import com.kkoalla.kkoallaspring.service.BreweryInfoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BookingController {
    @Autowired
    private BookingService bookingService;
    @Autowired
    private BreweryInfoService breweryInfoService;



    @PostMapping("/Booking")
    public ResponseEntity<String> createBooking(@Valid @RequestBody BookingDto bookingDto) {
        bookingService.createBooking(bookingDto);
        return ResponseEntity.ok("Booking created successfully");
    }
    @PostMapping("/BookingCountNumber")
    public ResponseEntity<String> createBookingCountNumber(@Valid @RequestBody BookingCountNumberDto bookingCountNumberDto) {
        bookingService.createBookingCountNumber(bookingCountNumberDto);
        return ResponseEntity.ok("BookingCountNumber created successfully");
    }

    @GetMapping("{breweryInfoId}/programs")
    public ResponseEntity<List<ProgramInfoDTO>> findProgramInfoId (@PathVariable("breweryInfoId") Long Id) {
        List<ProgramInfoDTO> programs = breweryInfoService.findProgramId(Id);
        return ResponseEntity.ok(programs);
    }

}
