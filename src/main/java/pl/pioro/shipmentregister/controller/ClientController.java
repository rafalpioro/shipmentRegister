package pl.pioro.shipmentregister.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.pioro.shipmentregister.entity.Client;

import pl.pioro.shipmentregister.exception.SourceNotFoundException;
import pl.pioro.shipmentregister.repository.ClientRepository;

import javax.transaction.Transactional;
import javax.validation.Valid;


@RestController
@Transactional
@CrossOrigin
@RequestMapping(path = "/clients", produces = "application/json")
public class ClientController {

    @Autowired
    private ClientRepository clientRepository;

    @GetMapping
    public Iterable<Client> findAllActive(@RequestParam(value = "sort", required = false) String sort, @RequestParam(value = "page", required = false) String page, @RequestParam(value = "size", required = false) String size) {
        if(sort !=null && page != null && size != null){
            Sort.Direction direction = sort.equals("asc") ? Sort.Direction.ASC : Sort.Direction.DESC;
            Sort sorted = Sort.by(direction, "name");
            Pageable pageable = PageRequest.of(Integer.parseInt(page), Integer.parseInt(size), sorted);

            return clientRepository.findAllByIsActiveTrue(pageable);

        } else {
            return clientRepository.findAllByIsActiveTrue();
        }
    }

    @PostMapping(consumes = "application/json")
    @ResponseStatus(value = HttpStatus.CREATED)
    public Client create(@Valid @RequestBody Client client){
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

    @GetMapping(path = "/name")
    public Client findByName(@RequestParam(value = "name") String name){
        Client client = clientRepository.findByName(name);
        if(client == null) return null;
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
    public Client updateClient(@PathVariable("id") long id, @Valid @RequestBody Client client) {
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
