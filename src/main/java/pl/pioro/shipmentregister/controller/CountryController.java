package pl.pioro.shipmentregister.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;
import pl.pioro.shipmentregister.entity.Country;

import pl.pioro.shipmentregister.repository.CountryRepository;


import javax.transaction.Transactional;


@RestController
@Transactional
@CrossOrigin
@RequestMapping(path = "countries", produces = "application/json")
public class CountryController {

    @Autowired
    private CountryRepository countryRepository;

    @GetMapping
    public Iterable<Country> findAll() {
        return countryRepository.findAll();
    }


}
