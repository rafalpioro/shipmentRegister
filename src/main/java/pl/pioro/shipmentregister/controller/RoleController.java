package pl.pioro.shipmentregister.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.pioro.shipmentregister.entity.Role;
import pl.pioro.shipmentregister.exception.SourceNotFoundException;
import pl.pioro.shipmentregister.repository.RoleRepository;


import javax.transaction.Transactional;

@RestController
@Transactional
@CrossOrigin
@RequestMapping(path = "/roles", produces = "application/json")
public class RoleController {


    @Autowired
    private RoleRepository roleRepository;

    @GetMapping
    public Iterable<Role> findAll() {
        return roleRepository.findAll();
    }

    @PostMapping(consumes = "application/json")
    @ResponseStatus(value = HttpStatus.CREATED)
    public Role create(@RequestBody Role role){
        return roleRepository.save(role);
    }

    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable("id") long id){
        if(roleRepository.findById(id) == null) throw new SourceNotFoundException("Source do not found: id= "+ id);
        roleRepository.deleteById(id);
    }

    @GetMapping(path = "/{id}")
    public Role findById(@PathVariable("id") long id){
        Role role = roleRepository.findById(id);
        if(role == null) throw new SourceNotFoundException("Source do not found: id= "+ id);
        return role;
    }

    @PutMapping(path = "/{id}", consumes = "application/json")
    public Role updateRole(@PathVariable("id") long id, @RequestBody Role role) {
        Role roleUpdated = roleRepository.findById(id);
        if(role == null) throw new SourceNotFoundException("Source do not found: id= "+ id);
        roleUpdated.setName(role.getName());

        return this.roleRepository.save(roleUpdated);
    }
}
