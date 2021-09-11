package co.com.poli.user.services;


import co.com.poli.commons.utils.Response;
import co.com.poli.user.entities.User;

import java.util.List;

public interface UserService {

    User findById(Long id);
    List<User> findAll();
    void save(User user);
    Response delete(User user);
}
