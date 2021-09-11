package co.com.poli.booking.clients;

import co.com.poli.booking.models.Showtime;
import co.com.poli.commons.utils.Response;
import co.com.poli.commons.utils.ResponseBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@ComponentScan("co.com.poli.commons")
public class ShowtimeClientFailBackHystrix implements ShowtimeClient{

    private final ResponseBuilder responseBuilder;

    @Override
    public Response findById(Long id) {
        Showtime showtime = new Showtime();
        showtime.setDate(null);
        showtime.setMovies(null);
        showtime.setShowtimeMovieList(null);
        return responseBuilder.failed(showtime);
    }
}
