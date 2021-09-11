package co.com.poli.movie.models;

import co.com.poli.commons.models.Movie;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class Showtime {

    private Long id;
    private Date date;
    private List<Long> movies;
    private List<Movie> showtimeMovieList;
}
