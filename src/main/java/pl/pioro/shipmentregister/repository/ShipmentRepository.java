package pl.pioro.shipmentregister.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RestResource;
import pl.pioro.shipmentregister.entity.Shipment;

import java.util.List;

@RestResource
public interface ShipmentRepository extends JpaRepository<Shipment, Long> {

    Shipment findById(long id);
    Iterable<Shipment> findAllByIsActiveTrue();
    Page<Shipment> findAllByIsActiveTrue(Pageable pageable);
    Page<Shipment> findAllByIsActiveFalse(Pageable pageable);
    Iterable<Shipment> findAllByIsActiveFalse();
    Page<Shipment> findAllByUserIdAndIsActiveTrue(Long id, Pageable pageable);
}
