package pl.pioro.shipmentregister.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.pioro.shipmentregister.entity.Role;
import pl.pioro.shipmentregister.repository.RoleRepository;


import javax.transaction.Transactional;

@RestController
@Transactional
@CrossOrigin
@RequestMapping(path = "/role", produces = "application/json")
public class RoleController {


    @Autowired
    private RoleRepository roleRepository;

    @GetMapping
    public Iterable<Role> findAll() {
        return roleRepository.findAll();
    }
}
