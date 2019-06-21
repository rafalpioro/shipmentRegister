package pl.pioro.shipmentregister.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.pioro.shipmentregister.entity.Shipment;
import pl.pioro.shipmentregister.exception.SourceNotFoundException;
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
    public Iterable<Shipment> findAll(@RequestParam(value = "page", required = false) String page, @RequestParam(value = "size", required = false) String size) {
        if(page != null && size != null) {
            PageRequest pageRequest = PageRequest.of(Integer.parseInt(page), Integer.parseInt(size));
            return shipmentRepository.findAllByIsActiveTrue(pageRequest);
        } else {
            return shipmentRepository.findAllByIsActiveTrue();
        }

    }

    @PostMapping(consumes = "application/json")
    @ResponseStatus(value = HttpStatus.CREATED)
    public Shipment create(@RequestBody Shipment shipment){
        return shipmentRepository.save(shipment);
    }

    @PatchMapping(path = "/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void shipmentDeactivated(@PathVariable long id){
        Shipment shipment = shipmentRepository.findById(id);
        if(shipment == null) throw new SourceNotFoundException("Source do not found: id= "+ id);
        shipment.setActive(false);
        shipmentRepository.save(shipment);
    }

    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable("id") long id){
        if(shipmentRepository.findById(id) == null) throw new SourceNotFoundException("Source do not found: id= "+ id);
        shipmentRepository.deleteById(id);
    }

    @GetMapping(path = "/{id}")
    public Shipment findById(@PathVariable("id") long id){
        Shipment shipment = shipmentRepository.findById(id);
        if(shipment == null) throw new SourceNotFoundException("Source do not found: id= "+ id);
        return shipment;
    }

    @PutMapping(path = "/{id}", consumes = "application/json")
    public Shipment updateShipment(@PathVariable("id") long id, @RequestBody Shipment shipment) {
        Shipment shipmentUpdated = shipmentRepository.findById(id);
        if(shipmentUpdated == null) throw new SourceNotFoundException("Source do not found: id= "+ id);
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
