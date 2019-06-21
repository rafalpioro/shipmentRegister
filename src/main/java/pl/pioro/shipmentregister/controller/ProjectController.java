package pl.pioro.shipmentregister.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.pioro.shipmentregister.entity.PackageType;
import pl.pioro.shipmentregister.entity.Project;
import pl.pioro.shipmentregister.repository.ProjectRepository;


import javax.transaction.Transactional;

@RestController
@Transactional
@CrossOrigin
@RequestMapping(path = "/projects", produces = "application/json")
public class ProjectController {

    @Autowired
    private ProjectRepository projectRepository;

    @GetMapping
    public Iterable<Project> findAll() {
        return projectRepository.findAll();
    }

    @PostMapping(consumes = "application/json")
    public Project create(@RequestBody Project project){
        return projectRepository.save(project);
    }

    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable("id") long id){
        projectRepository.deleteById(id);
    }

    @GetMapping(path = "/{id}")
    public Project findById(@PathVariable("id") long id){
        return projectRepository.findById(id);
    }

    @PutMapping(path = "/{id}", consumes = "application/json")
    public Project updateProject(@PathVariable("id") long id, @RequestBody Project project) {
        Project projectUpdated = projectRepository.findById(id);
        projectUpdated.setName(project.getName());
        projectUpdated.setNumber(project.getNumber());
        projectUpdated.setClient(project.getClient());
        projectUpdated.setStartDate(project.getStartDate());
        projectUpdated.setEndDate(project.getEndDate());
        projectUpdated.setProjectStatus(project.getProjectStatus());
        projectUpdated.setActive(project.getActive());

        return this.projectRepository.save(projectUpdated);
    }
}
