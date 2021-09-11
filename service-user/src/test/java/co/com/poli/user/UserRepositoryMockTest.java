package co.com.poli.user;

import co.com.poli.user.entities.User;
import co.com.poli.user.repositories.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

@DataJpaTest
public class UserRepositoryMockTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void when_findAllUsers_return_ListUsers(){
        User user = User.builder()
                .name("John")
                .lastName("Doe")
                .build();
        userRepository.save(user);

        List<User> users = userRepository.findAll();

        Assertions.assertThat(users.size()).isEqualTo(5);
    }
}
