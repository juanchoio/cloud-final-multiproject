package co.com.poli.showtimes.controllers;

import co.com.poli.commons.utils.FormatMessageErrorFields;
import co.com.poli.commons.utils.Response;
import co.com.poli.commons.utils.ResponseBuilder;
import co.com.poli.showtimes.entities.Showtime;
import co.com.poli.showtimes.services.ShowtimeService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/showtimes")
@ComponentScan("co.com.poli.commons")
public class ShowtimeController {

    private final ShowtimeService showtimeService;
    private final ResponseBuilder responseBuilder;
    private final FormatMessageErrorFields formatMessage;

    @GetMapping
    public Response findAll(){
        List<Showtime> showtimes =  showtimeService.findAll();
        return responseBuilder.success(showtimes);
    }

    @GetMapping("/{id}")
    public Response findById(@PathVariable("id") Long id){
        Showtime s = showtimeService.findById(id);
        if(s != null){
            return responseBuilder.success(s);
        }
        return responseBuilder.failedNotFound();
    }

    @PostMapping
    public Response save(@Valid @RequestBody Showtime showtime, BindingResult result){
        if (result.hasErrors()){
            return responseBuilder.failed(formatMessage.formatMessageError(result));
        }
        showtimeService.save(showtime);
        return responseBuilder.successCreated(showtime);
    }

    /****************************/
    /*TODO cambiar por @PutMapping*/

    @PutMapping("/{id}")
    public Response update(@PathVariable("id") Long id,
                           @Valid @RequestBody Showtime showtime,
                           BindingResult result){
        if (result.hasErrors()){
            return responseBuilder.failed(formatMessage.formatMessageError(result));
        }
        Showtime s = showtimeService.findById(id);
        if (s != null){
            s.setDate(showtime.getDate());
            s.setMovies(showtime.getMovies());
            showtimeService.save(s);
            return responseBuilder.success(s);
        }
        return responseBuilder.failedNotFound();
    }

}
