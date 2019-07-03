package pl.pioro.shipmentregister.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.pioro.shipmentregister.entity.ShipmentStatus;
import pl.pioro.shipmentregister.exception.SourceNotFoundException;
import pl.pioro.shipmentregister.repository.ShipmentStatusRepository;

import javax.transaction.Transactional;
import javax.validation.Valid;

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
    @ResponseStatus(value = HttpStatus.CREATED)
    public ShipmentStatus create(@RequestBody ShipmentStatus shipmentStatus){
        return shipmentStatusRepository.save(shipmentStatus);
    }

    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable("id") int id){
        if(shipmentStatusRepository.findById(id) == null) throw new SourceNotFoundException("Source do not found: id= "+ id);
        shipmentStatusRepository.deleteById(id);
    }

    @GetMapping(path = "/{id}")
    public ShipmentStatus findById(@PathVariable("id") int id){
        ShipmentStatus shipmentStatus = shipmentStatusRepository.findById(id);
        if(shipmentStatus == null) throw new SourceNotFoundException("Source do not found: id= "+ id);
        return shipmentStatus;
    }

    @PutMapping(path = "/{id}", consumes = "application/json")
    public ShipmentStatus updateShipmentStatus(@PathVariable("id") int id, @Valid @RequestBody ShipmentStatus shipmentStatus) {
        ShipmentStatus shipmentStatusUpdated = shipmentStatusRepository.findById(id);
        if(shipmentStatusUpdated == null) throw new SourceNotFoundException("Source do not found: id= "+ id);
        shipmentStatusUpdated.setName(shipmentStatus.getName());

        return this.shipmentStatusRepository.save(shipmentStatusUpdated);
    }
}
