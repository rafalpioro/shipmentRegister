package pl.pioro.shipmentregister.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.pioro.shipmentregister.entity.Carrier;
import pl.pioro.shipmentregister.exception.SourceNotFoundException;
import pl.pioro.shipmentregister.repository.CarrierRepository;

import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@Transactional
@CrossOrigin(maxAge = 3600)
@RequestMapping(path = "/carriers", produces = "application/json")
public class CarrierController {
    @Autowired
    private CarrierRepository carrierRepository;

    @GetMapping
    public Iterable<Carrier> findAllActive() {
        return carrierRepository.findAllByIsActiveTrue();
    }

    @GetMapping(path = "/name")
    public Carrier findByName(@RequestParam(value = "name") String name){
        Carrier carrier = carrierRepository.findByName(name);
        if(carrier == null) return null;
        return carrier;
    }

    @PostMapping(consumes = "application/json")
    @ResponseStatus(value = HttpStatus.CREATED)
    public Carrier create(@Valid @RequestBody Carrier carrier){
        return carrierRepository.save(carrier);
    }

    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable("id") long id){
        if(carrierRepository.findById(id) == null) throw new SourceNotFoundException("Source do not found: id= "+ id);
        carrierRepository.deleteById(id);
    }

    @GetMapping(path = "/{id}")
    public Carrier findById(@PathVariable("id") long id){
        Carrier carrier = carrierRepository.findById(id);
        if(carrier == null) throw new SourceNotFoundException("Source do not found: id= "+ id);
        return carrier;
    }

    @PatchMapping(path = "/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void clientDeactivated(@PathVariable long id) {
        Carrier carrier = carrierRepository.findById(id);
        if(carrier == null) throw new SourceNotFoundException("Source do not found: id= "+ id);
        carrier.setActive(false);
        carrierRepository.save(carrier);
    }

    @PutMapping(path = "/{id}", consumes = "application/json")
    public Carrier updateCarrier(@PathVariable("id") long id, @Valid @RequestBody Carrier carrier) {
        Carrier carrierUpdated = carrierRepository.findById(id);
        if(carrierUpdated == null) throw new SourceNotFoundException("Source do not found: id= "+ id);
        carrierUpdated.setName(carrier.getName());
        carrierUpdated.setCarrierType(carrier.getCarrierType());
        carrierUpdated.setActive(carrier.getActive());

        return this.carrierRepository.save(carrierUpdated);
    }
}
