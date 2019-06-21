package pl.pioro.shipmentregister.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.pioro.shipmentregister.entity.User;
import pl.pioro.shipmentregister.repository.UserRepository;

import javax.transaction.Transactional;

@RestController
@Transactional
@CrossOrigin
@RequestMapping(path = "admin/users", produces = "application/json")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public Iterable<User> findAll() {
        return userRepository.findAll();
    }

    @PostMapping(consumes = "application/json")
    public User create(@RequestBody User user){
        return userRepository.save(user);
    }

    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable("id") long id){
        userRepository.deleteById(id);
    }

    @GetMapping(path = "/{id}")
    public User findById(@PathVariable("id") long id){
        return userRepository.findById(id);
    }

    @PutMapping(path = "/{id}", consumes = "application/json")
    public User updateUser(@PathVariable("id") long id, @RequestBody User user) {
        User userUpdated = userRepository.findById(id);
        userUpdated.setName(user.getName());
        userUpdated.setRole(user.getRole());
        userUpdated.setPassword(user.getPassword());
        userUpdated.setEmail(user.getEmail());
        userUpdated.setActive(user.getActive());

        return this.userRepository.save(userUpdated);
    }
}
