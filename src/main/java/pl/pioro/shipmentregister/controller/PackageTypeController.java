package pl.pioro.shipmentregister.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.pioro.shipmentregister.entity.Country;
import pl.pioro.shipmentregister.entity.PackageType;
import pl.pioro.shipmentregister.exception.SourceNotFoundException;
import pl.pioro.shipmentregister.repository.PackageTypeRepository;


import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@Transactional
@CrossOrigin
@RequestMapping(path = "/admin/packagetypes", produces = "application/json")
public class PackageTypeController {

    @Autowired
    private PackageTypeRepository packageTypeRepository;

    @GetMapping
    public Iterable<PackageType> findAll() {
        return packageTypeRepository.findAll();
    }

    @PostMapping(consumes = "application/json")
    @ResponseStatus(value = HttpStatus.CREATED)
    public PackageType create(@Valid @RequestBody PackageType packageType){
        return packageTypeRepository.save(packageType);
    }

    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable("id") int id){
        if(packageTypeRepository.findById(id) == null) throw new SourceNotFoundException("Source do not found: id= "+ id);
        packageTypeRepository.deleteById(id);
    }

    @GetMapping(path = "/{id}")
    public PackageType findById(@PathVariable("id") int id){
        PackageType packageType = packageTypeRepository.findById(id);
        if(packageType == null) throw new SourceNotFoundException("Source do not found: id= "+ id);
        return packageType;
    }

    @PutMapping(path = "/{id}", consumes = "application/json")
    public PackageType updatePackageType(@PathVariable("id") int id, @Valid @RequestBody PackageType packageType) {
        PackageType packageTypeUpdated = packageTypeRepository.findById(id);
        if(packageTypeUpdated == null) throw new SourceNotFoundException("Source do not found: id= "+ id);
        packageTypeUpdated.setName(packageType.getName());

        return this.packageTypeRepository.save(packageTypeUpdated);
    }
}
