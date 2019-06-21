package pl.pioro.shipmentregister.repository;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RestResource;
import pl.pioro.shipmentregister.entity.Client;
import pl.pioro.shipmentregister.entity.Recipient;

@RestResource
public interface RecipientRepository extends JpaRepository<Recipient, Long> {

    Recipient findById(long id);
    Iterable<Recipient> findAllByIsActiveTrue();
    Iterable<Recipient> findAllByIsActiveTrue(PageRequest pageRequest);
}
