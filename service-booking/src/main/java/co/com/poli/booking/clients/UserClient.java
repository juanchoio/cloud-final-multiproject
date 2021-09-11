package co.com.poli.booking.clients;

import co.com.poli.commons.utils.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "service-user", fallback = UserClientFailBackHystrix.class)
public interface UserClient {

    @GetMapping("/users/{id}")
    Response findById(@PathVariable("id") Long id);

}
