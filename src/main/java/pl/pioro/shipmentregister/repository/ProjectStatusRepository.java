package pl.pioro.shipmentregister.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RestResource;
import pl.pioro.shipmentregister.entity.ProjectStatus;

@RestResource
public interface ProjectStatusRepository extends JpaRepository<ProjectStatus, Integer> {
    ProjectStatus findById(int id);
    ProjectStatus findByName(String name);
}
