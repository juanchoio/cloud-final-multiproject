package co.com.poli.movie.clients;

import co.com.poli.commons.utils.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "service-showtime", fallback = ShowtimeClientFailBackHystrix.class)
public interface ShowtimeClient {

    @GetMapping("/showtimes")
    public Response findAll();
}
