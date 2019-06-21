package pl.pioro.shipmentregister.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.pioro.shipmentregister.entity.Shipment;
import pl.pioro.shipmentregister.repository.ShipmentRepository;

import javax.transaction.Transactional;

@RestController
@Transactional
@CrossOrigin
@RequestMapping(path = "/shipments", produces = "application/json")
public class ShipmentController {

    @Autowired
    private ShipmentRepository shipmentRepository;

    @GetMapping
    public Iterable<Shipment> findAll() {
        return shipmentRepository.findAll();
    }

    @PostMapping(consumes = "application/json")
    public Shipment create(@RequestBody Shipment shipment){
        return shipmentRepository.save(shipment);
    }

    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable("id") long id){
        shipmentRepository.deleteById(id);
    }

    @GetMapping(path = "/{id}")
    public Shipment findById(@PathVariable("id") long id){
        return shipmentRepository.findById(id);
    }

    @PutMapping(path = "/{id}", consumes = "application/json")
    public Shipment updateShipment(@PathVariable("id") long id, @RequestBody Shipment shipment) {
        Shipment shipmentUpdated = shipmentRepository.findById(id);
        shipmentUpdated.setBranch(shipment.getBranch());
        shipmentUpdated.setCarrier(shipment.getCarrier());
        shipmentUpdated.setDeliveryDate(shipment.getDeliveryDate());
        shipmentUpdated.setMrn(shipment.getMrn());
        shipmentUpdated.setRecipient(shipment.getRecipient());
        shipmentUpdated.setPod(shipment.getPod());
        shipmentUpdated.setProject(shipment.getProject());
        shipmentUpdated.setSendDate(shipment.getSendDate());
        shipmentUpdated.setActive(shipment.getActive());
        shipmentUpdated.setShipmentStatus(shipment.getShipmentStatus());
        shipmentUpdated.setUser(shipment.getUser());
        shipmentUpdated.setDeliveryDate(shipment.getDeliveryDate());

        return this.shipmentRepository.save(shipmentUpdated);
    }
}
