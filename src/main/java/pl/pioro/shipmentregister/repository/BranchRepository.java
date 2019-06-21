package pl.pioro.shipmentregister.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RestResource;
import pl.pioro.shipmentregister.entity.Branch;

@RestResource
public interface BranchRepository extends JpaRepository<Branch, Long> {
    Branch findById(long id);
}
