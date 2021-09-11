package co.com.poli.movie.clients;

import co.com.poli.commons.utils.Response;
import co.com.poli.commons.utils.ResponseBuilder;
import co.com.poli.movie.models.Booking;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
@ComponentScan("co.com.poli.commons")
public class BookingClientFailBackHystrix implements BookingClient{

    private final ResponseBuilder responseBuilder;

    @Override
    public Response findAll() {
        List<Booking> bookings = new ArrayList<>();
        bookings.add(null);
        return responseBuilder.failed(bookings);
    }
}
