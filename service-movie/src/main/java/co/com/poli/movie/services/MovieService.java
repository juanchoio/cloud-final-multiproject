package co.com.poli.movie.services;

import co.com.poli.commons.utils.Response;
import co.com.poli.movie.entities.Movie;

import java.util.List;

public interface MovieService {

    Movie findbyId(Long id);
    List<Movie> findAll();
    void save(Movie movie);
    Response delete(Movie movie);
}
