package spring.boot.rest.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.boot.rest.repository.UserRepository;
import spring.boot.rest.user.User;


import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public List<User> listUser() {
        return userRepository.findAll();
    }
}
