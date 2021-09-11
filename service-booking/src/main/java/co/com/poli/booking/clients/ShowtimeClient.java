package co.com.poli.booking.clients;

import co.com.poli.commons.utils.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "service-showtime", fallback = ShowtimeClientFailBackHystrix.class)
public interface ShowtimeClient {

    @GetMapping("/showtimes/{id}")
    Response findById(@PathVariable("id") Long id);
}
