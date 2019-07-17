package pl.pioro.shipmentregister.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RestResource;
import pl.pioro.shipmentregister.entity.Project;

@RestResource
public interface ProjectRepository extends JpaRepository<Project, Long> {
    Project findById(long id);
    Project findByNumber(String name);
    Iterable<Project> findAllByIsActiveTrue();
    Page<Project> findAllByIsActiveTrue(Pageable pageable);
}
