package pl.pioro.shipmentregister.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RestResource;
import pl.pioro.shipmentregister.entity.Carrier;
import pl.pioro.shipmentregister.entity.CarrierType;

@RestResource
public interface CarrierTypeRepository extends JpaRepository<CarrierType, Integer> {
    CarrierType findById(int id);
    CarrierType findByName(String name);
}
