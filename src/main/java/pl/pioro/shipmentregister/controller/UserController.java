package pl.pioro.shipmentregister.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import pl.pioro.shipmentregister.entity.User;
import pl.pioro.shipmentregister.exception.SourceNotFoundException;
import pl.pioro.shipmentregister.repository.UserRepository;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@Transactional
@CrossOrigin
@RequestMapping(path = "/admin-all/users", produces = "application/json")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;



    @PostMapping(path = "/",consumes = "application/json")
    @ResponseStatus(value = HttpStatus.CREATED)
    public User createUser(@Valid @RequestBody User user){
        String password = user.getPassword();
        user.setPassword(passwordEncoder.encode(password));
        return userRepository.save(user);
    }

    @PatchMapping(path = "/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void projectDeactivated(@PathVariable long id){
        User user = userRepository.findById(id);
        if(user == null) throw new SourceNotFoundException("Source do not found: id= "+ id);
        user.setActive(false);
        userRepository.save(user);
    }

    @DeleteMapping(path = "/{id}")
    public void deleteUser(@PathVariable("id") long id){
        if(userRepository.findById(id) == null) throw new SourceNotFoundException("Source do not found: id= "+ id);
        userRepository.deleteById(id);
    }

    @GetMapping(path = "/{id}")
    public User findUserById(@PathVariable("id") long id){
        User user = userRepository.findById(id);
        if(user == null) throw new SourceNotFoundException("Source do not found: id= "+ id);
        return user;
    }

    @PutMapping(path = "/{id}", consumes = "application/json")
    public User updateUser(@PathVariable("id") long id, @RequestBody User user) {
        User userUpdated = userRepository.findById(id);
        if(userUpdated == null) throw new SourceNotFoundException("Source do not found: id= "+ id);
        userUpdated.setName(user.getName());
        userUpdated.setRole(user.getRole());
        userUpdated.setPassword(user.getPassword());
        userUpdated.setEmail(user.getEmail());
        userUpdated.setActive(user.getActive());

        return this.userRepository.save(userUpdated);
    }


    @GetMapping("/name")
    public User findCurrentUser(@RequestParam(value = "email") String email) {

            return userRepository.findByEmail(email.trim());
    }


}
