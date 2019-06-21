package pl.pioro.shipmentregister.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.pioro.shipmentregister.entity.Country;
import pl.pioro.shipmentregister.entity.PackageType;
import pl.pioro.shipmentregister.repository.PackageTypeRepository;


import javax.transaction.Transactional;

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
    public PackageType create(@RequestBody PackageType packageType){
        return packageTypeRepository.save(packageType);
    }

    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable("id") int id){
        packageTypeRepository.deleteById(id);
    }

    @GetMapping(path = "/{id}")
    public PackageType findById(@PathVariable("id") int id){
        return packageTypeRepository.findById(id);
    }

    @PutMapping(path = "/{id}", consumes = "application/json")
    public PackageType updatePackageType(@PathVariable("id") int id, @RequestBody PackageType packageType) {
        PackageType packageTypeUpdated = packageTypeRepository.findById(id);
        packageTypeUpdated.setName(packageType.getName());

        return this.packageTypeRepository.save(packageTypeUpdated);
    }
}
