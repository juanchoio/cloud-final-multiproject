package co.com.poli.booking.clients;

import co.com.poli.commons.models.Movie;
import co.com.poli.commons.utils.Response;
import co.com.poli.commons.utils.ResponseBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@ComponentScan("co.com.poli.commons")
public class MovieClientFailBackHystrix implements MovieClient{

    private final ResponseBuilder responseBuilder;

    @Override
    public Response findById(Long id) {
        Movie movie = new Movie();
        movie.setTitle("");
        movie.setDirector("");
        movie.setRating(0);

        return responseBuilder.failed(movie);
    }
}
