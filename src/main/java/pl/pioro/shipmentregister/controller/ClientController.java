package pl.pioro.shipmentregister.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.pioro.shipmentregister.entity.Client;

import pl.pioro.shipmentregister.exception.SourceNotFoundException;
import pl.pioro.shipmentregister.repository.ClientRepository;

import javax.transaction.Transactional;


@RestController
@Transactional
@CrossOrigin
@RequestMapping(path = "/clients", produces = "application/json")
public class ClientController {

    @Autowired
    private ClientRepository clientRepository;

    @GetMapping
    public Iterable<Client> findAllActive(@RequestParam(value = "page", required = false) String page, @RequestParam(value = "size", required = false) String size) {
        if(page != null && size != null){
            int pageInt = Integer.parseInt(page);
            int sizeInt = Integer.parseInt(size);
            PageRequest findWithPage = PageRequest.of(pageInt, sizeInt);
            return clientRepository.findAllByIsActiveTrue(findWithPage);
        } else {
            return clientRepository.findAllByIsActiveTrue();
        }
    }

    @PostMapping(consumes = "application/json")
    @ResponseStatus(value = HttpStatus.CREATED)
    public Client create(@RequestBody Client client){
        return clientRepository.save(client);
    }

    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable("id") long id){
        if(clientRepository.findById(id) == null) throw new SourceNotFoundException("Source do not found: id= "+ id);
        clientRepository.deleteById(id);
    }

    @GetMapping(path = "/{id}")
    public Client findById(@PathVariable("id") long id){
        Client client = clientRepository.findById(id);
        if(client == null) throw new SourceNotFoundException("Source do not found: id= "+ id);
        return client;
    }

    @PatchMapping(path = "/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void clientDeactivated(@PathVariable long id) {
        Client client = clientRepository.findById(id);
        if(client == null) throw new SourceNotFoundException("Source do not found: id= "+ id);
        client.setActive(false);
        clientRepository.save(client);
    }

    @PutMapping(path = "/{id}", consumes = "application/json")
    public Client updateClient(@PathVariable("id") long id, @RequestBody Client client) {
        Client clientUpdated = clientRepository.findById(id);
        if(clientUpdated == null) throw new SourceNotFoundException("Source do not found: id= "+ id);
        clientUpdated.setName(client.getName());
        clientUpdated.setAddress(client.getAddress());
        clientUpdated.setCity(client.getCity());
        clientUpdated.setCountry(client.getCountry());
        clientUpdated.setZipCode(client.getZipCode());
        clientUpdated.setActive(client.getActive());

        return this.clientRepository.save(clientUpdated);
    }
}
