package pl.pioro.shipmentregister.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.pioro.shipmentregister.entity.Recipient;
import pl.pioro.shipmentregister.exception.SourceNotFoundException;
import pl.pioro.shipmentregister.repository.RecipientRepository;


import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@Transactional
@CrossOrigin
@RequestMapping(path = "/recipients", produces = "application/json")
public class RecipientController {

    @Autowired
    private RecipientRepository recipientRepository;

    @GetMapping
    public Iterable<Recipient> findAllActive(@RequestParam(value = "page", required = false) String page, @RequestParam(value = "size", required = false) String size) {
        if(page != null && size != null) {
            Pageable pageable = PageRequest.of(Integer.parseInt(page), Integer.parseInt(size));
            return recipientRepository.findAllByIsActiveTrue(pageable);
        } else {
            return recipientRepository.findAllByIsActiveTrue();
        }

    }

    @PostMapping(consumes = "application/json")
    @ResponseStatus(value = HttpStatus.CREATED)
    public Recipient create(@Valid @RequestBody Recipient recipient){
        return recipientRepository.save(recipient);
    }

    @PatchMapping(path = "/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void recipientDeactivated(@PathVariable long id){
        Recipient recipient = recipientRepository.findById(id);
        if(recipient == null) throw new SourceNotFoundException("Source do not found: id= "+ id);
        recipient.setActive(false);
        recipientRepository.save(recipient);
    }

    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable("id") long id){
        if(recipientRepository.findById(id) == null) throw new SourceNotFoundException("Source do not found: id= "+ id);
        recipientRepository.deleteById(id);
    }

    @GetMapping(path = "/{id}")
    public Recipient findById(@PathVariable("id") long id){
        Recipient recipient = recipientRepository.findById(id);
        if(recipient == null) throw new SourceNotFoundException("Source do not found: id= "+ id);
        return recipient;
    }

    @GetMapping(path = "/")
    public Recipient findByName(@RequestParam(value = "name") String name){
        Recipient recipient = recipientRepository.findByName(name);
        if(recipient == null) throw new SourceNotFoundException("Source do not found: name= "+ name);
        return recipient;
    }

    @PutMapping(path = "/{id}", consumes = "application/json")
    public Recipient updateRecipient(@PathVariable("id") long id, @Valid @RequestBody Recipient recipient) {
        Recipient recipientUpdated = recipientRepository.findById(id);
        if(recipientUpdated == null) throw new SourceNotFoundException("Source do not found: id= "+ id);
        recipientUpdated.setName(recipient.getName());
        recipientUpdated.setAddress(recipient.getAddress());
        recipientUpdated.setCity(recipient.getCity());
        recipientUpdated.setZipCode(recipient.getZipCode());
        recipientUpdated.setCountry(recipient.getCountry());
        recipientUpdated.setActive(recipient.getActive());

        return this.recipientRepository.save(recipientUpdated);
    }
}
