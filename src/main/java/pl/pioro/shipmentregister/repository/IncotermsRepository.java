package pl.pioro.shipmentregister.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.pioro.shipmentregister.entity.Incoterms;

public interface IncotermsRepository extends JpaRepository<Incoterms, Integer> {
   Incoterms findById(int id);
}
