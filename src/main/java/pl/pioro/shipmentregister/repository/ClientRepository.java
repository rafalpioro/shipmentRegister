package pl.pioro.shipmentregister.repository;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RestResource;
import pl.pioro.shipmentregister.entity.Client;

@RestResource
public interface ClientRepository extends JpaRepository<Client, Long> {
    Client findById(long id);
    Iterable<Client> findAllByIsActiveTrue();
    Iterable<Client> findAllByIsActiveTrue(PageRequest pageRequest);

}
