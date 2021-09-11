package co.com.poli.movie;

import co.com.poli.commons.utils.ResponseBuilder;
import co.com.poli.movie.clients.BookingClient;
import co.com.poli.movie.clients.ShowtimeClient;
import co.com.poli.movie.entities.Movie;
import co.com.poli.movie.repositories.MovieRepository;
import co.com.poli.movie.services.MovieService;
import co.com.poli.movie.services.MovieServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
public class MovieServiceMockTest {

    @Mock
    private MovieRepository movieRepository;
    private MovieService movieService;
    private BookingClient bookingClient;
    private ShowtimeClient showtimeClient;
    private ResponseBuilder responseBuilder;

    @BeforeEach
    public void begin(){
        MockitoAnnotations.initMocks(this);
        movieService = new MovieServiceImpl(movieRepository,
                                            bookingClient,
                                            showtimeClient,
                                            responseBuilder);

        Movie movie = Movie.builder()
                .id(5L)
                .title("Ladrones de élite")
                .director("Renny Harlin")
                .rating(5)
                .build();
        Mockito.when(movieRepository.findById(5L))
                .thenReturn(Optional.of(movie));
    }

    @Test
    public void when_findById_return_movie(){
        Movie movie = movieService.findbyId(5L);
        Assertions.assertThat(movie.getTitle()).isEqualTo("Ladrones de élite");
    }
}
