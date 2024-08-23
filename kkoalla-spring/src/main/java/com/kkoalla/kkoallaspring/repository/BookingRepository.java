package com.kkoalla.kkoallaspring.repository;


import com.kkoalla.kkoallaspring.entity.Booking;
import jdk.jfr.Registered;
import org.springframework.data.jpa.repository.JpaRepository;

@Registered
public interface BookingRepository extends JpaRepository<Booking,Long>  {
}
