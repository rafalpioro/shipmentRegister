package pl.pioro.shipmentregister.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RestResource;
import pl.pioro.shipmentregister.entity.Role;

@RestResource
public interface RoleRepository extends JpaRepository<Role, Long> {
}
