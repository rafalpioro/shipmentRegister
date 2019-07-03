package pl.pioro.shipmentregister.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import pl.pioro.shipmentregister.entity.*;
import pl.pioro.shipmentregister.exception.SourceNotFoundException;
import pl.pioro.shipmentregister.repository.*;

import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@Transactional
@CrossOrigin
@RequestMapping(path = "/admin", produces = "application/json")
public class AdminController {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private RecipientRepository recipientRepository;

    @Autowired
    private ShipmentRepository shipmentRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/clients")
    public Iterable<Client> findAllClients(@RequestParam(value = "page", required = false) String page, @RequestParam(value = "size", required = false) String size) {
        if(page != null && size != null){
            int pageInt = Integer.parseInt(page);
            int sizeInt = Integer.parseInt(size);
            PageRequest findWithPage = PageRequest.of(pageInt, sizeInt);
            return clientRepository.findAll(findWithPage);
        } else {
            return clientRepository.findAll();
        }
    }

    @GetMapping("/projects")
    public Iterable<Project> findAllProjects(@RequestParam(value = "page", required = false) String page, @RequestParam(value = "size", required = false) String size) {
        if(page != null && size != null) {
            PageRequest pageRequest = PageRequest.of(Integer.parseInt(page), Integer.parseInt(size));
            return projectRepository.findAll(pageRequest);
        } else {
            return projectRepository.findAll();
        }
    }

    @GetMapping("/recipients")
    public Iterable<Recipient> findAllRecipients(@RequestParam(value = "page", required = false) String page, @RequestParam(value = "size", required = false) String size) {
        if(page != null && size != null) {
            PageRequest pageRequest = PageRequest.of(Integer.parseInt(page), Integer.parseInt(size));
            return recipientRepository.findAll(pageRequest);
        } else {
            return recipientRepository.findAll();
        }
    }


    @GetMapping("/shipments")
    public Iterable<Shipment> findAllShipments(@RequestParam(value = "page", required = false) String page, @RequestParam(value = "size", required = false) String size) {
        if(page != null && size != null) {
            PageRequest pageRequest = PageRequest.of(Integer.parseInt(page), Integer.parseInt(size));
            return shipmentRepository.findAll(pageRequest);
        } else {
            return shipmentRepository.findAll();
        }
    }

    @GetMapping("/users")
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
    public User create(@Valid @RequestBody User user){
        String password = user.getPassword();
        user.setPassword(passwordEncoder.encode(password));
        return userRepository.save(user);
    }

    @PatchMapping(path = "/users/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void projectDeactivated(@PathVariable long id){
        User user = userRepository.findById(id);
        if(user == null) throw new SourceNotFoundException("Source do not found: id= "+ id);
        user.setActive(false);
        userRepository.save(user);
    }

    @DeleteMapping(path = "/users/{id}")
    public void delete(@PathVariable("id") long id){
        if(userRepository.findById(id) == null) throw new SourceNotFoundException("Source do not found: id= "+ id);
        userRepository.deleteById(id);
    }

    @GetMapping(path = "/users/{id}")
    public User findById(@PathVariable("id") long id){
        User user = userRepository.findById(id);
        if(user == null) throw new SourceNotFoundException("Source do not found: id= "+ id);
        return user;
    }

    @PutMapping(path = "/users/{id}", consumes = "application/json")
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
