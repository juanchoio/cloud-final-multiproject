package co.com.poli.booking.services;

import co.com.poli.booking.entities.Booking;

import java.util.List;

public interface BookingService {

    Booking findById(Long id);
    Booking findByUserId(Long userId);
    List<Booking> findAll();
    void save(Booking booking);
    void delete(Booking booking);

}
