package pl.pioro.shipmentregister.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;
import pl.pioro.shipmentregister.entity.*;
import pl.pioro.shipmentregister.repository.*;
import javax.transaction.Transactional;


@RestController
@Transactional
@CrossOrigin
@RequestMapping(path = "/admin-all", produces = "application/json")
public class AdminController {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private RecipientRepository recipientRepository;

    @Autowired
    private ShipmentRepository shipmentRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CarrierRepository carrierRepository;


    @GetMapping("/users")
    public Iterable<User> findAll(@RequestParam(value = "page", required = false) String page, @RequestParam(value = "size", required = false) String size) {
        if(page != null && size != null){
            int pageInt = Integer.parseInt(page);
            int sizeInt = Integer.parseInt(size);
            PageRequest findWithPage = PageRequest.of(pageInt, sizeInt);
            return userRepository.findAll(findWithPage);
        } else {
            return userRepository.findAll();
        }
    }


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

    @GetMapping("/projects")
    public Iterable<Project> findAllProjects(@RequestParam(value = "page", required = false) String page, @RequestParam(value = "size", required = false) String size) {
        if(page != null && size != null) {
            PageRequest pageRequest = PageRequest.of(Integer.parseInt(page), Integer.parseInt(size));
            return projectRepository.findAll(pageRequest);
        } else {
            return projectRepository.findAll();
        }
    }

    @GetMapping("/recipients")
    public Iterable<Recipient> findAllRecipients(@RequestParam(value = "page", required = false) String page, @RequestParam(value = "size", required = false) String size) {
        if(page != null && size != null) {
            PageRequest pageRequest = PageRequest.of(Integer.parseInt(page), Integer.parseInt(size));
            return recipientRepository.findAll(pageRequest);
        } else {
            return recipientRepository.findAll();
        }
    }


    @GetMapping("/shipments-deactivated")
    public Iterable<Shipment> findAllDeactivatedShipments(@RequestParam(value = "page", required = false) String page, @RequestParam(value = "size", required = false) String size) {
        if(page != null && size != null) {
            PageRequest pageRequest = PageRequest.of(Integer.parseInt(page), Integer.parseInt(size));
            return shipmentRepository.findAllByIsActiveFalse(pageRequest);
        } else {
            return shipmentRepository.findAllByIsActiveFalse();
        }
    }

    @GetMapping("/carriers")
    public Iterable<Carrier> findAllCarriers() {
        return carrierRepository.findAll();
    }



}
