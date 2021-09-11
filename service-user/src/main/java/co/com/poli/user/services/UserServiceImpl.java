package co.com.poli.user.services;

import co.com.poli.commons.utils.Response;
import co.com.poli.commons.utils.ResponseBuilder;
import co.com.poli.user.clients.BookingClient;
import co.com.poli.user.entities.User;
import co.com.poli.user.models.Booking;
import co.com.poli.user.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@ComponentScan("co.com.poli.commons")
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    private final BookingClient bookingClient;
    private final ResponseBuilder responseBuilder;

    @Override
    @Transactional(readOnly = true)
    public User findById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Response delete(User user) {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        Booking booking = modelMapper.map(bookingClient.findByUserId(user.getId()).getData(), Booking.class);
        System.out.println("servicio: " + booking);
        if (booking.getId() != null) {
            if (booking.getId() == 0) {
                return responseBuilder.failed("No se puede eliminar: " +
                        "El servicio Booking no esta disponible");
            }
            if (booking.getUserId() != null) {
                return responseBuilder.failed("No se puede eliminar: " +
                        "El usuario tiene reservas asociadas");
            }
        }
        userRepository.delete(user);
        return responseBuilder.successDeleted(user);
    }
}
