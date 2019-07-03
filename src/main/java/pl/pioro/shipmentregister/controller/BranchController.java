package pl.pioro.shipmentregister.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.pioro.shipmentregister.entity.Branch;
import pl.pioro.shipmentregister.exception.SourceNotFoundException;
import pl.pioro.shipmentregister.repository.BranchRepository;


import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@Transactional
@CrossOrigin
@RequestMapping(path = "/admin/branches", produces = "application/json")
public class BranchController {

    @Autowired
    private BranchRepository branchRepository;

    @GetMapping
    public Iterable<Branch> findAll() {
        return branchRepository.findAll();
    }

    @PostMapping(consumes = "application/json")
    @ResponseStatus(value = HttpStatus.CREATED)
    public Branch create(@Valid @RequestBody Branch branch){
        return branchRepository.save(branch);
    }

    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable("id") long id){
        if(branchRepository.findById(id) == null) throw new SourceNotFoundException("Source do not found: id= "+ id);
        branchRepository.deleteById(id);
    }

    @GetMapping(path = "/{id}")
    public Branch findById(@PathVariable("id") long id){
        Branch branch = branchRepository.findById(id);
        if(branch == null) throw new SourceNotFoundException("Source do not found: id= "+ id);
        return branch;
    }

    @PutMapping(path = "/{id}", consumes = "application/json")
    public Branch updateBranch(@PathVariable("id") long id, @Valid @RequestBody Branch branch) {
        Branch branchUpdated = branchRepository.findById(id);
        if(branchUpdated == null) throw new SourceNotFoundException("Source do not found: id= "+ id);
        branchUpdated.setName(branch.getName());
        branchUpdated.setCity(branch.getCity());
        branchUpdated.setAddress(branch.getAddress());
        branchUpdated.setCountry(branch.getCountry());
        branchUpdated.setZipCode(branch.getZipCode());

        return this.branchRepository.save(branchUpdated);
    }
}
