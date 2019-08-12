package pl.pioro.shipmentregister.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.pioro.shipmentregister.entity.CarrierType;
import pl.pioro.shipmentregister.exception.SourceNotFoundException;
import pl.pioro.shipmentregister.repository.CarrierTypeRepository;

import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@Transactional
@CrossOrigin
@RequestMapping(path = "/admin/carriertypes", produces = "application/json")
public class CarrierTypeController {

    @Autowired
    private CarrierTypeRepository carrierTypeRepository;

    @GetMapping
    public Iterable<CarrierType> findAll() {
        return carrierTypeRepository.findAll();
    }

    @PostMapping(consumes = "application/json")
    @ResponseStatus(value = HttpStatus.CREATED)
    public CarrierType create(@Valid @RequestBody CarrierType carrierType){
        return carrierTypeRepository.save(carrierType);
    }

    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable("id") int id){
        if(carrierTypeRepository.findById(id) == null) throw new SourceNotFoundException("Source do not found: id= "+ id);
        carrierTypeRepository.deleteById(id);
    }

    @GetMapping(path = "/{id}")
    public CarrierType findById(@PathVariable("id") int id){
        CarrierType carrierType = carrierTypeRepository.findById(id);
        if(carrierType == null) throw new SourceNotFoundException("Source do not found: id= "+ id);
        return carrierType;
    }

    @GetMapping(path = "/name")
    public CarrierType findByName(@RequestParam(value = "name") String name){
        CarrierType carrierType = carrierTypeRepository.findByName(name);
        if(carrierType == null) return null;
        return carrierType;
    }

    @PutMapping(path = "/{id}", consumes = "application/json")
    public CarrierType updateCarrier(@PathVariable("id") int id, @Valid @RequestBody CarrierType carrierType) {
        CarrierType carrierTypeUpdated = carrierTypeRepository.findById(id);
        if(carrierTypeUpdated == null) throw new SourceNotFoundException("Source do not found: id= "+ id);
        carrierTypeUpdated.setName(carrierType.getName());

        return this.carrierTypeRepository.save(carrierTypeUpdated);
    }
}
