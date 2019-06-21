package pl.pioro.shipmentregister.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RestResource;
import pl.pioro.shipmentregister.entity.Shipment;

@RestResource
public interface ShipmentRepository extends JpaRepository<Shipment, Long> {

    Shipment findById(long id);
}
