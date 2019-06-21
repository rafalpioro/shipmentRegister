package pl.pioro.shipmentregister.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.pioro.shipmentregister.entity.ProjectStatus;
import pl.pioro.shipmentregister.exception.SourceNotFoundException;
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
    @ResponseStatus(value = HttpStatus.CREATED)
    public ProjectStatus create(@RequestBody ProjectStatus projectStatus){
        return projectStatusRepository.save(projectStatus);
    }

    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable("id") int id){
        if(projectStatusRepository.findById(id) == null) throw new SourceNotFoundException("Source do not found: id= "+ id);
        projectStatusRepository.deleteById(id);
    }

    @GetMapping(path = "/{id}")
    public ProjectStatus findById(@PathVariable("id") int id){
        ProjectStatus projectStatus = projectStatusRepository.findById(id);
        if(projectStatus == null) throw new SourceNotFoundException("Source do not found: id= "+ id);
        return projectStatus;
    }

    @PutMapping(path = "/{id}", consumes = "application/json")
    public ProjectStatus updateProjectStatus(@PathVariable("id") int id, @RequestBody ProjectStatus projectStatus) {
        ProjectStatus projectStatusUpdated = projectStatusRepository.findById(id);
        if(projectStatusUpdated == null) throw new SourceNotFoundException("Source do not found: id= "+ id);
        projectStatusUpdated.setName(projectStatus.getName());

        return this.projectStatusRepository.save(projectStatusUpdated);
    }
}
