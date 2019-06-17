package pl.pioro.shipmentregister.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.pioro.shipmentregister.entity.User;
import pl.pioro.shipmentregister.repository.UserRepository;

import javax.transaction.Transactional;

@RestController
@Transactional
@CrossOrigin
@RequestMapping(path = "/users", produces = "application/json")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public Iterable<User> findAll() {
        return userRepository.findAll();
    }
}
