package co.com.poli.movie.services;

import co.com.poli.commons.utils.Response;
import co.com.poli.commons.utils.ResponseBuilder;
import co.com.poli.movie.clients.BookingClient;
import co.com.poli.movie.clients.ShowtimeClient;
import co.com.poli.movie.entities.Movie;
import co.com.poli.movie.models.Booking;
import co.com.poli.movie.models.Showtime;
import co.com.poli.movie.repositories.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@ComponentScan("co.com.poli.commons")
public class MovieServiceImpl implements MovieService{

    private final MovieRepository movieRepository;
    private final BookingClient bookingClient;
    private final ShowtimeClient showtimeClient;
    private final ResponseBuilder responseBuilder;

    @Override
    @Transactional(readOnly = true)
    public Movie findbyId(Long id) {
        return movieRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Movie> findAll() {
        return movieRepository.findAll();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(Movie movie) {
        movieRepository.save(movie);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Response delete(Movie movie) {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        List<Booking> bookings = Arrays.asList(modelMapper.map(bookingClient.findAll().getData(), Booking[].class));
        System.out.println("servicio: " + bookings);
        List<Showtime> showtimes = Arrays.asList(modelMapper.map(showtimeClient.findAll().getData(), Showtime[].class));
        System.out.println("servicio: " + showtimes);
        if (bookings.contains(null) || showtimes.contains(null)){
            return responseBuilder.failed("Error: El sevicio  no esta disponible " +
                    "para comprobar si la pelicula (" + movie.getTitle() + ") esta asociada en alguna reserva " +
                    "o programacion. Intente mas tarde");
        }
        List<Long> bookingsMoviesId = bookings.stream()
                .flatMap(booking -> booking.getMovies().stream())
                .collect(Collectors.toList());
        System.out.println("Servicio: Ids movies en Booking " + bookingsMoviesId);
        List<Long> showtimesMoviesId = showtimes.stream()
                .flatMap(showtime -> showtime.getMovies().stream())
                .collect(Collectors.toList());
        System.out.println("Servicio: Ids movies en Showtime " + showtimesMoviesId);
        if (bookingsMoviesId.contains(movie.getId())){
            return responseBuilder.failed("Error: No se puede eliminar, " +
                    "la pelicula (" + movie.getTitle() + ") esta asociada a una o mas reservas");
        }
        if (showtimesMoviesId.contains(movie.getId())){
            return responseBuilder.failed("Error: No se puede eliminar, " +
                    "la pelicula (" + movie.getTitle() + ") esta asociada a una o mas programaciones");
        }
        movieRepository.delete(movie);
        return responseBuilder.successDeleted(movie);
    }
}
