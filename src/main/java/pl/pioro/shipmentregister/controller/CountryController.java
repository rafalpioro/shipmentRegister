package pl.pioro.shipmentregister.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.pioro.shipmentregister.entity.Country;
import pl.pioro.shipmentregister.repository.CountryRepository;


import javax.transaction.Transactional;

@RestController
@Transactional
@CrossOrigin
@RequestMapping(path = "admin/countries", produces = "application/json")
public class CountryController {

    @Autowired
    private CountryRepository countryRepository;

    @GetMapping
    public Iterable<Country> findAll() {
        return countryRepository.findAll();
    }

    @PostMapping(consumes = "application/json")
    public Country create(@RequestBody Country country){
        return countryRepository.save(country);
    }

    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable("id") long id){
        countryRepository.deleteById(id);
    }

    @GetMapping(path = "/{id}")
    public Country findById(@PathVariable("id") long id){
        return countryRepository.findById(id);
    }

    @PutMapping(path = "/{id}", consumes = "application/json")
    public Country updateCountry(@PathVariable("id") long id, @RequestBody Country country) {
        Country countryUpdated = countryRepository.findById(id);
        countryUpdated.setName(country.getName());
        countryUpdated.setCode(country.getCode());

        return this.countryRepository.save(countryUpdated);
    }

}
