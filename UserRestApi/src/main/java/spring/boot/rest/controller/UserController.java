package spring.boot.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.web.bind.annotation.*;
import spring.boot.rest.repository.RoleRepository;
import spring.boot.rest.repository.UserRepository;
import spring.boot.rest.service.UserService;
import spring.boot.rest.user.Role;
import spring.boot.rest.user.Status;
import spring.boot.rest.user.User;


import javax.validation.Valid;
import java.util.List;

@RestController
public class UserController {
    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    UserService userService;


    @GetMapping("/users")
    public ResponseEntity<List<User>> getAll() {
        return new ResponseEntity<>(userService.listUser(), HttpStatus.OK);
    }


    @CrossOrigin()
    @PostMapping("/users/register")
    public Status registerUser(@Valid @RequestBody User newUser) {
        List<User> users = userRepository.findAll();

        for (User user : users) {
            if (user.equals(newUser)) {
                System.out.println("User Already exists!");
                return Status.USER_ALREADY_EXISTS;
            }
        }

        userRepository.save(newUser);
        return Status.SUCCESS;
    }

    @CrossOrigin()
    @PostMapping("/users/login")
    public Status loginUser(@Valid @RequestBody Role role) {
        List<Role> roles = roleRepository.findAll();

        for (Role other : roles) {
            if (other.equals(role)) {
                role.setLoggedIn(true);
                roleRepository.save(role);
                return Status.SUCCESS;
            }
        }

        return Status.FAILURE;
    }

    @CrossOrigin()
    @PostMapping("/users/logout")
    public Status logUserOut(@Valid @RequestBody Role user) {
        List<Role> users = roleRepository.findAll();

        for (Role other : users) {
            if (other.equals(user)) {
                user.setLoggedIn(false);
                roleRepository.save(user);
                return Status.SUCCESS;
            }
        }

        return Status.FAILURE;
    }

    @CrossOrigin()
    @DeleteMapping("/users/all")
    public Status deleteUsers() {
        userRepository.deleteAll();
        return Status.SUCCESS;
    }
}
