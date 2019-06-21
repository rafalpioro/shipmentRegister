package pl.pioro.shipmentregister.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.pioro.shipmentregister.entity.Client;

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
    public Iterable<Client> findAll() {
        return clientRepository.findAll();
    }

    @PostMapping(consumes = "application/json")
    public Client create(@RequestBody Client client){
        return clientRepository.save(client);
    }

    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable("id") long id){
        clientRepository.deleteById(id);
    }

    @GetMapping(path = "/{id}")
    public Client findById(@PathVariable("id") long id){
        return clientRepository.findById(id);
    }

    @PutMapping(path = "/{id}", consumes = "application/json")
    public Client updateClient(@PathVariable("id") long id, @RequestBody Client client) {
        Client clientUpdated = clientRepository.findById(id);
        clientUpdated.setName(client.getName());
        clientUpdated.setAddress(client.getAddress());
        clientUpdated.setCity(client.getCity());
        clientUpdated.setCountry(client.getCountry());
        clientUpdated.setZipCode(client.getZipCode());
        clientUpdated.setActive(client.getActive());

        return this.clientRepository.save(clientUpdated);
    }
}
