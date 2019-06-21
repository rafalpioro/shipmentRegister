package pl.pioro.shipmentregister.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RestResource;
import pl.pioro.shipmentregister.entity.Carrier;

@RestResource
public interface CarrierRepository extends JpaRepository<Carrier, Long> {
    Carrier findById(long id);
}
