package pl.pioro.shipmentregister.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.pioro.shipmentregister.entity.Role;
import pl.pioro.shipmentregister.repository.RoleRepository;


import javax.transaction.Transactional;

@RestController
@Transactional
@CrossOrigin
@RequestMapping(path = "admin/roles", produces = "application/json")
public class RoleController {


    @Autowired
    private RoleRepository roleRepository;

    @GetMapping
    public Iterable<Role> findAll() {
        return roleRepository.findAll();
    }

    @PostMapping(consumes = "application/json")
    public Role create(@RequestBody Role role){
        return roleRepository.save(role);
    }

    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable("id") long id){
        roleRepository.deleteById(id);
    }

    @GetMapping(path = "/{id}")
    public Role findById(@PathVariable("id") long id){
        return roleRepository.findById(id);
    }

    @PutMapping(path = "/{id}", consumes = "application/json")
    public Role updateRole(@PathVariable("id") long id, @RequestBody Role role) {
        Role roleUpdated = roleRepository.findById(id);
        roleUpdated.setName(role.getName());

        return this.roleRepository.save(roleUpdated);
    }
}
