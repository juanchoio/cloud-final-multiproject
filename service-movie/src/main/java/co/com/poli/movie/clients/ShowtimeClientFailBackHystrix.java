package co.com.poli.movie.clients;

import co.com.poli.commons.utils.Response;
import co.com.poli.commons.utils.ResponseBuilder;
import co.com.poli.movie.models.Showtime;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
@ComponentScan("co.com.poli.commons")
public class ShowtimeClientFailBackHystrix implements ShowtimeClient{

    private final ResponseBuilder responseBuilder;

    @Override
    public Response findAll() {
        List<Showtime> showtimes = new ArrayList<>();
        showtimes.add(null);
        return responseBuilder.failed(showtimes);
    }
}
