package co.com.poli.movie;

import co.com.poli.movie.entities.Movie;
import co.com.poli.movie.repositories.MovieRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

@DataJpaTest
public class MovieRepositoryMockTest {

    @Autowired
    private MovieRepository movieRepository;

    @Test
    public void when_findByRating_return_ListMovie(){
        Movie movie = Movie.builder()
                .title("Ladrones de élite")
                .director("Renny Harlin")
                .rating(5)
                .build();

        movieRepository.save(movie);

        List<Movie> movies = movieRepository.findByRating(movie.getRating());

        Assertions.assertThat(movies.size()).isEqualTo(3);
    }
}
