package co.com.poli.movie.clients;

import co.com.poli.commons.utils.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "service-booking", fallback = BookingClientFailBackHystrix.class)
public interface BookingClient {

    @GetMapping("/bookings")
    public Response findAll();

}
