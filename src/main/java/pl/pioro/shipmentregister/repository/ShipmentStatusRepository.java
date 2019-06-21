package pl.pioro.shipmentregister.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RestResource;
import pl.pioro.shipmentregister.entity.ShipmentStatus;

@RestResource
public interface ShipmentStatusRepository extends JpaRepository<ShipmentStatus, Integer> {
    ShipmentStatus findById(int id);
}
