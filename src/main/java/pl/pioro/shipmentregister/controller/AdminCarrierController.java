package pl.pioro.shipmentregister.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.pioro.shipmentregister.entity.Carrier;
import pl.pioro.shipmentregister.repository.CarrierRepository;

import javax.transaction.Transactional;

@RestController
@Transactional
@CrossOrigin
@RequestMapping(path = "/admin-all", produces = "application/json")
public class AdminCarrierController {

    @Autowired
    private CarrierRepository carrierRepository;

    @GetMapping("/carriers")
    public Iterable<Carrier> findAllCarriers() {
        return carrierRepository.findAll();
    }
}
