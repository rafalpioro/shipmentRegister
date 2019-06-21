package pl.pioro.shipmentregister.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RestResource;
import pl.pioro.shipmentregister.entity.Project;

@RestResource
public interface ProjectRepository extends JpaRepository<Project, Long> {
    Project findById(long id);
}
