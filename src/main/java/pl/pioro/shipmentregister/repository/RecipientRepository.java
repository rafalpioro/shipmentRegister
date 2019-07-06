package pl.pioro.shipmentregister.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RestResource;
import pl.pioro.shipmentregister.entity.Recipient;

@RestResource
public interface RecipientRepository extends JpaRepository<Recipient, Long> {

    Recipient findById(long id);
    Recipient findByName(String name);
    Iterable<Recipient> findAllByIsActiveTrue();
    Page<Recipient> findAllByIsActiveTrue(Pageable pageable);
}
