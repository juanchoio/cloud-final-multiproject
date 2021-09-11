package co.com.poli.movie.controllers;

import co.com.poli.commons.utils.FormatMessageErrorFields;
import co.com.poli.commons.utils.Response;
import co.com.poli.commons.utils.ResponseBuilder;
import co.com.poli.movie.entities.Movie;
import co.com.poli.movie.services.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/movies")
@ComponentScan("co.com.poli.commons")
public class MovieController {

    private final MovieService movieService;
    private final ResponseBuilder responseBuilder;
    private final FormatMessageErrorFields formatMessage;


    @GetMapping
    public Response findAll(){
        List<Movie> movies = movieService.findAll();
        return responseBuilder.success(movies);
    }

    @GetMapping("/{id}")
    public Response findById(@PathVariable("id") Long id){
        Movie m = movieService.findbyId(id);
        if (m != null){
            return responseBuilder.success(m);
        }
        return responseBuilder.failedNotFound();
    }

    @PostMapping
    public Response save(@Valid @RequestBody Movie movie, BindingResult result){
        if (result.hasErrors()){
            return responseBuilder.failed(formatMessage.formatMessageError(result));
        }
        movieService.save(movie);
        return responseBuilder.successCreated(movie);
    }

    @DeleteMapping("/{id}")
    public Response delete(@PathVariable("id") Long id){
        Movie movie = movieService.findbyId(id);
        if (movie == null){
            return responseBuilder.failedNotFound();
        }
        return movieService.delete(movie);
    }

}
