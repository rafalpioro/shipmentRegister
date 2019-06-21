package pl.pioro.shipmentregister.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.pioro.shipmentregister.entity.Recipient;
import pl.pioro.shipmentregister.repository.RecipientRepository;


import javax.transaction.Transactional;

@RestController
@Transactional
@CrossOrigin
@RequestMapping(path = "/recipients", produces = "application/json")
public class RecipientController {

    @Autowired
    private RecipientRepository recipientRepository;

    @GetMapping
    public Iterable<Recipient> findAll() {
        return recipientRepository.findAll();
    }

    @PostMapping(consumes = "application/json")
    public Recipient create(@RequestBody Recipient recipient){
        return recipientRepository.save(recipient);
    }

    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable("id") long id){
        recipientRepository.deleteById(id);
    }

    @GetMapping(path = "/{id}")
    public Recipient findById(@PathVariable("id") long id){
        return recipientRepository.findById(id);
    }

    @PutMapping(path = "/{id}", consumes = "application/json")
    public Recipient updateRecipient(@PathVariable("id") long id, @RequestBody Recipient recipient) {
        Recipient recipientUpdated = recipientRepository.findById(id);
        recipientUpdated.setName(recipient.getName());
        recipientUpdated.setAddress(recipient.getAddress());
        recipientUpdated.setCity(recipient.getCity());
        recipientUpdated.setZipCode(recipient.getZipCode());
        recipientUpdated.setCountry(recipient.getCountry());
        recipientUpdated.setActive(recipient.getActive());

        return this.recipientRepository.save(recipientUpdated);
    }
}
