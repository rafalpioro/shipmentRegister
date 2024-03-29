package pl.pioro.shipmentregister.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RestResource;
import pl.pioro.shipmentregister.entity.Client;

@RestResource
public interface ClientRepository extends JpaRepository<Client, Long> {
    Client findById(long id);
    Client findByName(String name);
    Iterable<Client> findAllByIsActiveTrue();
    Page<Client> findAllByIsActiveTrue(Pageable pageable);

}
