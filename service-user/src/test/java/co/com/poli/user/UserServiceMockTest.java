package co.com.poli.user;

import co.com.poli.commons.utils.ResponseBuilder;
import co.com.poli.user.clients.BookingClient;
import co.com.poli.user.entities.User;
import co.com.poli.user.repositories.UserRepository;
import co.com.poli.user.services.UserService;
import co.com.poli.user.services.UserServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
public class UserServiceMockTest {

    @Mock
    private UserRepository userRepository;
    private UserService userService;
    private BookingClient bookingClient;
    private ResponseBuilder responseBuilder;

    @BeforeEach
    public void begin(){
        MockitoAnnotations.initMocks(this);
        userService = new UserServiceImpl(userRepository,
                                          bookingClient,
                                          responseBuilder);

        User user = User.builder()
                .id(5L)
                .name("John")
                .lastName("Doe")
                .build();
        Mockito.when(userRepository.findById(4L))
                .thenReturn(Optional.of(user));
    }

    @Test
    public void when_findbyid_return_user(){
        User user = userService.findById(4L);
        Assertions.assertThat(user.getName()).isEqualTo("John");
    }

}
