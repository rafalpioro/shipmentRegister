package pl.pioro.shipmentregister.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import pl.pioro.shipmentregister.entity.Carrier;

import pl.pioro.shipmentregister.repository.CarrierRepository;

import javax.transaction.Transactional;

@RestController
@Transactional
@CrossOrigin
@RequestMapping(path = "/carriers", produces = "application/json")
public class CarrierController {
    @Autowired
    private CarrierRepository carrierRepository;

    @GetMapping
    public Iterable<Carrier> findAll() {
        return carrierRepository.findAll();
    }

    @PostMapping(consumes = "application/json")
    public Carrier create(@RequestBody Carrier carrier){
        return carrierRepository.save(carrier);
    }

    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable("id") long id){
        carrierRepository.deleteById(id);
    }

    @GetMapping(path = "/{id}")
    public Carrier findById(@PathVariable("id") long id){
        return carrierRepository.findById(id);
    }

    @PutMapping(path = "/{id}", consumes = "application/json")
    public Carrier updateCarrier(@PathVariable("id") long id, @RequestBody Carrier carrier) {
        Carrier carrierUpdated = carrierRepository.findById(id);
        carrierUpdated.setName(carrier.getName());
        carrierUpdated.setCarrierType(carrier.getCarrierType());

        return this.carrierRepository.save(carrierUpdated);
    }
}
