package pl.pioro.shipmentregister.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.pioro.shipmentregister.entity.Country;

import pl.pioro.shipmentregister.exception.SourceNotFoundException;
import pl.pioro.shipmentregister.repository.CountryRepository;


import javax.transaction.Transactional;
import javax.validation.Valid;


@RestController
@Transactional
@CrossOrigin
@RequestMapping(path = "/admin/countries", produces = "application/json")
public class CountryController {

    @Autowired
    private CountryRepository countryRepository;

    @GetMapping
    public Iterable<Country> findAll() {
        return countryRepository.findAll();
    }


    @PostMapping( consumes = "application/json")
    @ResponseStatus(value = HttpStatus.CREATED)
    public Country createCountry(@Valid @RequestBody Country country){
        return countryRepository.save(country);
    }

    @DeleteMapping(path = "/{id}")
    public void deleteCountry(@PathVariable("id") long id){
        if(countryRepository.findById(id) == null) throw new SourceNotFoundException("Source do not found: id= "+ id);
        countryRepository.deleteById(id);
    }

    @GetMapping(path = "/{id}")
    public Country findCountryById(@PathVariable("id") long id){
        Country country = countryRepository.findById(id);
        if(country == null) throw new SourceNotFoundException("Source do not found: id= "+ id);
        return country;
    }

    @PutMapping(path = "/{id}", consumes = "application/json")
    public Country updateCountry(@PathVariable("id") long id, @Valid @RequestBody Country country) {
        Country countryUpdated = countryRepository.findById(id);
        if(countryUpdated == null) throw new SourceNotFoundException("Source do not found: id= "+ id);
        countryUpdated.setName(country.getName());
        countryUpdated.setCode(country.getCode());

        return this.countryRepository.save(countryUpdated);
    }

}
