package co.com.poli.booking.clients;

import co.com.poli.booking.models.User;
import co.com.poli.commons.utils.Response;
import co.com.poli.commons.utils.ResponseBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@ComponentScan("co.com.poli.commons")
public class UserClientFailBackHystrix implements UserClient{

    private final ResponseBuilder responseBuilder;

    @Override
    public Response findById(Long id) {
        User user = new User();
        user.setName("");
        user.setLastName("");
        return responseBuilder.failed(user);
    }
}
