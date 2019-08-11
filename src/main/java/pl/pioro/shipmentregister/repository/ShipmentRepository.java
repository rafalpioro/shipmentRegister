package pl.pioro.shipmentregister.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RestResource;
import pl.pioro.shipmentregister.entity.Shipment;
import pl.pioro.shipmentregister.model.CountUserShipment;

import java.util.List;

@RestResource
public interface ShipmentRepository extends JpaRepository<Shipment, Long> {

    Shipment findById(long id);
    Iterable<Shipment> findAllByIsActiveTrue();
    Iterable<Shipment> findAllByUserIdAndIsActiveTrue(Long id);
    Page<Shipment> findAllByIsActiveTrue(Pageable pageable);
    Page<Shipment> findAllByIsActiveFalse(Pageable pageable);
    Iterable<Shipment> findAllByIsActiveFalse();
    Page<Shipment> findAllByUserIdAndIsActiveTrue(Long id, Pageable pageable);

    @Query("select new pl.pioro.shipmentregister.model.CountUserShipment( s.user.email, COUNT(s.user.id)) FROM Shipment s GROUP BY s.user.id")
    List<CountUserShipment> findUserShipmentsGroupByUser();
}
