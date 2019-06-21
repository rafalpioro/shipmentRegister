package pl.pioro.shipmentregister.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RestResource;
import pl.pioro.shipmentregister.entity.Country;

@RestResource
public interface CountryRepository extends JpaRepository<Country, Long> {
    Country findById(long id);
}
