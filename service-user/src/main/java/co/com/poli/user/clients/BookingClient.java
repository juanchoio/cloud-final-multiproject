package co.com.poli.user.clients;

import co.com.poli.commons.utils.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "service-booking", fallback = BookingClientFailBackHystrix.class)
public interface BookingClient {

    @GetMapping("/bookings/{userId}")
    Response findByUserId(@PathVariable("userId")Long userId);
}
