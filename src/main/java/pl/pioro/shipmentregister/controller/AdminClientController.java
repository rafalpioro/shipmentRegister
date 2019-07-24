package pl.pioro.shipmentregister.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;
import pl.pioro.shipmentregister.entity.Client;
import pl.pioro.shipmentregister.repository.ClientRepository;

import javax.transaction.Transactional;

@RestController
@Transactional
@CrossOrigin
@RequestMapping(path = "/admin-all", produces = "application/json")
public class AdminClientController {

    @Autowired
    private ClientRepository clientRepository;

    @GetMapping("/clients")
    public Iterable<Client> findAllClients(@RequestParam(value = "page", required = false) String page, @RequestParam(value = "size", required = false) String size) {
        if(page != null && size != null){
            int pageInt = Integer.parseInt(page);
            int sizeInt = Integer.parseInt(size);
            PageRequest findWithPage = PageRequest.of(pageInt, sizeInt);
            return clientRepository.findAll(findWithPage);
        } else {
            return clientRepository.findAll();
        }
    }
}
