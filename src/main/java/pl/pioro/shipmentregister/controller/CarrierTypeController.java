package pl.pioro.shipmentregister.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.pioro.shipmentregister.entity.CarrierType;
import pl.pioro.shipmentregister.repository.CarrierTypeRepository;

import javax.transaction.Transactional;

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
    public CarrierType create(@RequestBody CarrierType carrierType){
        return carrierTypeRepository.save(carrierType);
    }

    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable("id") int id){
        carrierTypeRepository.deleteById(id);
    }

    @GetMapping(path = "/{id}")
    public CarrierType findById(@PathVariable("id") int id){
        return carrierTypeRepository.findById(id);
    }

    @PutMapping(path = "/{id}", consumes = "application/json")
    public CarrierType updateCarrier(@PathVariable("id") int id, @RequestBody CarrierType carrierType) {
        CarrierType carrierTypeUpdated = carrierTypeRepository.findById(id);
        carrierTypeUpdated.setName(carrierType.getName());

        return this.carrierTypeRepository.save(carrierTypeUpdated);
    }
}
