package co.com.poli.booking.services.utils;

import co.com.poli.booking.clients.MovieClient;
import co.com.poli.booking.clients.ShowtimeClient;
import co.com.poli.booking.clients.UserClient;
import co.com.poli.booking.entities.Booking;
import co.com.poli.booking.models.Showtime;
import co.com.poli.booking.models.User;
import co.com.poli.commons.models.Movie;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class BookingServiceCommon {

    private final ShowtimeClient showtimeClient;
    private final UserClient userClient;
    private final MovieClient movieClient;

    public void getMovies(Booking booking){
        ModelMapper modelMapper = new ModelMapper();
        //seteamos el user en Booking obteniendolo primero con FeignClient
        User user = modelMapper.map(userClient.findById(booking.getUserId()).getData(), User.class);
        booking.setUser(user);

        //obtenemos el Showtime por el id con FeignClient
        Showtime showtime = modelMapper.map(showtimeClient.findById(booking.getShowtimeId()).getData(), Showtime.class);

        //arreglo de tipo Long que contiene los id de las peliculas de Booking
        List<Long> moviesBookingId = booking.getMovies();
        System.out.println("Arreglo de Id peliculas Booking" + moviesBookingId);

        //arreglo de tipo Long que contiene los id de las peliculas de Showtime
        List<Long> moviesShowtimeId = showtime.getMovies();
        System.out.println("Arreglo de Id peliculas Showtime" + moviesShowtimeId);

        //obtenemos las peliculas del Showtime si el servicio esta disponible
        List<Movie> moviesShowtime = new ArrayList<>();
        if (moviesShowtimeId != null){
            moviesShowtime = moviesShowtimeId.stream()
                    .map(movieId -> modelMapper.map(movieClient.findById(movieId).getData(), Movie.class))
                    .collect(Collectors.toList());
            //seteamos la lista de peliculas primero en Showtime y luego seteamos Showtime en Booking
            System.out.println("Lista de pelis: " + moviesShowtime);
        }
        showtime.setShowtimeMovieList(moviesShowtime);
        booking.setShowtime(showtime);

        //obtenemos las peliculas del Booking
        List<Movie> moviesBooking = moviesBookingId.stream()
                .map(movieId -> modelMapper.map(movieClient.findById(movieId).getData(), Movie.class))
                .collect(Collectors.toList());
        //seteamos la lista de peliculas en Booking
        booking.setMovieList(moviesBooking);
        //return booking;
    }

            /*ModelMapper modelMapper = new ModelMapper();
            User user = modelMapper.map(userClient.findById(booking.getUserId()).getData(), User.class);
            booking.setUser(user);//seteamos el user a Booking
            Showtime showtime = modelMapper.map(showtimeClient.findById(booking.getShowtimeId()).getData(), Showtime.class);
            List<Long> moviesBookingId = booking.getMovies();//arreglo de ID's de peliculas de Booking
            List<Long> moviesShowtimeId = showtime.getMovies();//arreglo de ID's de peliculas de Showtime
            List<Movie> moviesShowtime = new ArrayList<>();//obtenemos las peliculas del Showtime si el servicio esta disponible
            if (moviesShowtimeId != null){
                moviesShowtime = moviesShowtimeId.stream()
                        .map(movieId -> modelMapper.map(movieClient.findById(movieId).getData(), Movie.class))
                        .collect(Collectors.toList());
                //seteamos la lista de peliculas primero en Showtime y luego seteamos Showtime en Booking
                System.out.println("Lista de pelis: " + moviesShowtime);
            }
            showtime.setShowtimeMovieList(moviesShowtime);
            booking.setShowtime(showtime);
            //obtenemos las peliculas del Booking
            List<Movie> moviesBooking = moviesBookingId.stream()
                    .map(movieId -> modelMapper.map(movieClient.findById(movieId).getData(), Movie.class))
                    .collect(Collectors.toList());
            booking.setMovieList(moviesBooking);//seteamos la lista de peliculas en Booking*/

}
