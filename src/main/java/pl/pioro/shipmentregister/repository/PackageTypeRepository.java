package pl.pioro.shipmentregister.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RestResource;
import pl.pioro.shipmentregister.entity.PackageType;

@RestResource
public interface PackageTypeRepository extends JpaRepository<PackageType, Integer> {
    PackageType findById(int id);
}
