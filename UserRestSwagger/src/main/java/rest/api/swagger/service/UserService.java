package rest.api.swagger.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rest.api.swagger.repository.UserRepository;
import rest.api.swagger.user.User;


import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public List<User> listUser() {
        return userRepository.findAll();
    }
}
