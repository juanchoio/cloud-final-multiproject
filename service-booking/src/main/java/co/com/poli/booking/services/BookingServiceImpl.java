package co.com.poli.booking.services;

import co.com.poli.booking.entities.Booking;
import co.com.poli.booking.repositories.BookingRepository;
import co.com.poli.booking.services.utils.BookingServiceCommon;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService{

    private final BookingRepository bookingRepository;
    private final BookingServiceCommon bookingServiceCommon;

    @Override
    @Transactional(readOnly = true)
    public Booking findById(Long id) {
        Booking booking = bookingRepository.findById(id).orElse(null);
        if (booking != null){
            bookingServiceCommon.getMovies(booking);
            return bookingRepository.findById(id).orElse(null);
        }
        return booking;
    }

    @Override
    @Transactional(readOnly = true)
    public Booking findByUserId(Long userId) {
        Booking booking = bookingRepository.findByUserId(userId);
        if (booking != null){
            bookingServiceCommon.getMovies(booking);
            return bookingRepository.findByUserId(userId);
        }
        return booking;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Booking> findAll() {
        List<Booking> bookings = bookingRepository.findAll();
        if (bookings.size() > 0){
            bookings.stream()
                    .map(booking -> {
                        bookingServiceCommon.getMovies(booking);
                        return booking;
                    }).collect(Collectors.toList());
        }
        return bookings;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(Booking booking) {
        bookingRepository.save(booking);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Booking booking) {
        bookingRepository.delete(booking);
    }
}
