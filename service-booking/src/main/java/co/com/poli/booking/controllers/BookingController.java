package co.com.poli.booking.controllers;

import co.com.poli.booking.entities.Booking;
import co.com.poli.booking.services.BookingService;
import co.com.poli.commons.utils.FormatMessageErrorFields;
import co.com.poli.commons.utils.Response;
import co.com.poli.commons.utils.ResponseBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/bookings")
@RequiredArgsConstructor
@ComponentScan("co.com.poli.commons")
public class BookingController {

    private final BookingService bookingService;
    private final ResponseBuilder responseBuilder;
    private final FormatMessageErrorFields formatMessage;


    @GetMapping
    public Response findAll(){
        List<Booking> bookings = bookingService.findAll();
        return responseBuilder.success(bookings);
    }

    @GetMapping("/detail/{id}")
    public Response findById(@PathVariable("id") Long id){
        Booking b = bookingService.findById(id);
        if (b != null){
            return responseBuilder.success(b);
        }
        return responseBuilder.failedNotFound();
    }

    @GetMapping("/{userId}")
    public Response findByUserId(@PathVariable("userId") Long userId){
        Booking b = bookingService.findByUserId(userId);
        if (b != null){
            return responseBuilder.success(b);
        }
        return responseBuilder.failedNotFound();
    }

    @PostMapping
    public Response save(@Valid @RequestBody Booking booking, BindingResult result){
        if (result.hasErrors()){
            return responseBuilder.failed(formatMessage.formatMessageError(result));
        }
        bookingService.save(booking);
        return responseBuilder.successCreated(booking);
    }

    @DeleteMapping("/{id}")
    public Response delete(@PathVariable("id") Long id){
        Booking b = bookingService.findById(id);
        if (b != null){
            bookingService.delete(b);
            return responseBuilder.successDeleted(b);
        }
        return responseBuilder.failedNotFound();
    }


}
