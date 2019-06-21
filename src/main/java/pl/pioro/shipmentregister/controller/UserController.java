package pl.pioro.shipmentregister.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.pioro.shipmentregister.entity.Project;
import pl.pioro.shipmentregister.entity.User;
import pl.pioro.shipmentregister.exception.SourceNotFoundException;
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
    public Iterable<User> findAll(@RequestParam(value = "page", required = false) String page, @RequestParam(value = "size", required = false) String size) {
        if(page != null && size != null){
            int pageInt = Integer.parseInt(page);
            int sizeInt = Integer.parseInt(size);
            PageRequest findWithPage = PageRequest.of(pageInt, sizeInt);
            return userRepository.findAll(findWithPage);
        } else {
            return userRepository.findAll();
        }
    }

    @PostMapping(consumes = "application/json")
    @ResponseStatus(value = HttpStatus.CREATED)
    public User create(@RequestBody User user){
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
    public void delete(@PathVariable("id") long id){
        if(userRepository.findById(id) == null) throw new SourceNotFoundException("Source do not found: id= "+ id);
        userRepository.deleteById(id);
    }

    @GetMapping(path = "/{id}")
    public User findById(@PathVariable("id") long id){
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
}
