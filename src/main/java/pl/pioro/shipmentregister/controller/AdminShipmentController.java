package pl.pioro.shipmentregister.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;
import pl.pioro.shipmentregister.entity.Shipment;
import pl.pioro.shipmentregister.model.CountUserShipment;
import pl.pioro.shipmentregister.repository.ShipmentRepository;
import javax.transaction.Transactional;
import java.util.List;

@RestController
@Transactional
@CrossOrigin
@RequestMapping(path = "/admin-all", produces = "application/json")
public class AdminShipmentController {

    @Autowired
    private ShipmentRepository shipmentRepository;

    @GetMapping("/shipments-deactivated")
    public Iterable<Shipment> findAllDeactivatedShipments(@RequestParam(value = "page", required = false) String page, @RequestParam(value = "size", required = false) String size) {
        if(page != null && size != null) {
            PageRequest pageRequest = PageRequest.of(Integer.parseInt(page), Integer.parseInt(size));
            return shipmentRepository.findAllByIsActiveFalse(pageRequest);
        } else {
            return shipmentRepository.findAllByIsActiveFalse();
        }
    }

    @GetMapping(path = "/stat/count-user-shipments")
    public List<CountUserShipment> userShipments() {
        return this.shipmentRepository.findUserShipmentsGroupByUser();
    }
}
