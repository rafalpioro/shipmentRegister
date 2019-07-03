package pl.pioro.shipmentregister.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
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
    public User findCurrentUser(@RequestParam(value = "email") String email) {

            return userRepository.findByEmail(email.trim());
    }


}
