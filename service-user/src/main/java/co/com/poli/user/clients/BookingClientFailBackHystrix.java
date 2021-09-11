package co.com.poli.user.clients;

import co.com.poli.commons.utils.Response;
import co.com.poli.commons.utils.ResponseBuilder;
import co.com.poli.user.models.Booking;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@ComponentScan("co.com.poli.commons")
public class BookingClientFailBackHystrix implements BookingClient{

    private final ResponseBuilder responseBuilder;

    @Override
    public Response findByUserId(Long userId) {
        Booking booking = new Booking();
        booking.setId(0L);
        booking.setUserId(null);
        booking.setShowtimeId(null);
        booking.setMovies(null);

        return responseBuilder.failed(booking);
    }
}
