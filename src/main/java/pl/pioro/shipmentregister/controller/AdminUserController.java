package pl.pioro.shipmentregister.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;
import pl.pioro.shipmentregister.entity.User;
import pl.pioro.shipmentregister.repository.UserRepository;

import javax.transaction.Transactional;

@RestController
@Transactional
@CrossOrigin
@RequestMapping(path = "/admin-all", produces = "application/json")
public class AdminUserController {

    @Autowired
    private UserRepository userRepository;

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
}
