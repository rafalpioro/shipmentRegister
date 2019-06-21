package pl.pioro.shipmentregister.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.pioro.shipmentregister.entity.ShipmentStatus;
import pl.pioro.shipmentregister.repository.ShipmentStatusRepository;

import javax.transaction.Transactional;

@RestController
@Transactional
@CrossOrigin
@RequestMapping(path = "/admin/shipmentstatus", produces = "application/json")
public class ShipmentStatusController {

    @Autowired
    private ShipmentStatusRepository shipmentStatusRepository;

    @GetMapping
    public Iterable<ShipmentStatus> findAll() {
        return shipmentStatusRepository.findAll();
    }

    @PostMapping(consumes = "application/json")
    public ShipmentStatus create(@RequestBody ShipmentStatus shipmentStatus){
        return shipmentStatusRepository.save(shipmentStatus);
    }

    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable("id") int id){
        shipmentStatusRepository.deleteById(id);
    }

    @GetMapping(path = "/{id}")
    public ShipmentStatus findById(@PathVariable("id") int id){
        return shipmentStatusRepository.findById(id);
    }

    @PutMapping(path = "/{id}", consumes = "application/json")
    public ShipmentStatus updateShipmentStatus(@PathVariable("id") int id, @RequestBody ShipmentStatus shipmentStatus) {
        ShipmentStatus shipmentStatusUpdated = shipmentStatusRepository.findById(id);
        shipmentStatusUpdated.setName(shipmentStatus.getName());

        return this.shipmentStatusRepository.save(shipmentStatusUpdated);
    }
}
