package pl.pioro.shipmentregister.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.pioro.shipmentregister.entity.ProjectStatus;
import pl.pioro.shipmentregister.repository.ProjectStatusRepository;

import javax.transaction.Transactional;

@RestController
@Transactional
@CrossOrigin
@RequestMapping(path = "/admin/projectstatus", produces = "application/json")
public class ProjectStatusController {

    @Autowired
    private ProjectStatusRepository projectStatusRepository;

    @GetMapping
    public Iterable<ProjectStatus> findAll() {
        return projectStatusRepository.findAll();
    }

    @PostMapping(consumes = "application/json")
    public ProjectStatus create(@RequestBody ProjectStatus projectStatus){
        return projectStatusRepository.save(projectStatus);
    }

    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable("id") int id){
        projectStatusRepository.deleteById(id);
    }

    @GetMapping(path = "/{id}")
    public ProjectStatus findById(@PathVariable("id") int id){
        return projectStatusRepository.findById(id);
    }

    @PutMapping(path = "/{id}", consumes = "application/json")
    public ProjectStatus updateProjectStatus(@PathVariable("id") int id, @RequestBody ProjectStatus projectStatus) {
        ProjectStatus projectStatusUpdated = projectStatusRepository.findById(id);
        projectStatusUpdated.setName(projectStatus.getName());

        return this.projectStatusRepository.save(projectStatusUpdated);
    }
}
