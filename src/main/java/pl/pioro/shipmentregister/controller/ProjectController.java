package pl.pioro.shipmentregister.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.pioro.shipmentregister.repository.ProjectRepository;
import pl.pioro.shipmentregister.entity.Project;
import pl.pioro.shipmentregister.exception.SourceNotFoundException;


import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@Transactional
@CrossOrigin
@RequestMapping(path = "/projects", produces = "application/json")
public class ProjectController {

    @Autowired
    private ProjectRepository projectRepository;

    @GetMapping
    public Iterable<Project> findAllActive(@RequestParam(value = "page", required = false) String page, @RequestParam(value = "size", required = false) String size) {
        if(page != null && size != null) {
            Pageable pageable = PageRequest.of(Integer.parseInt(page), Integer.parseInt(size));
            return projectRepository.findAllByIsActiveTrue(pageable);
        } else {
            return projectRepository.findAllByIsActiveTrue();
        }
    }

    @PostMapping(consumes = "application/json")
    @ResponseStatus(value = HttpStatus.CREATED)
    public Project create(@Valid @RequestBody Project project){
        return projectRepository.save(project);
    }

    @PatchMapping(path = "/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void projectDeactivated(@PathVariable long id){
        Project project = projectRepository.findById(id);
        if(project == null) throw new SourceNotFoundException("Source do not found: id= "+ id);
        project.setActive(false);
        projectRepository.save(project);
    }

    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable("id") long id){
        if(projectRepository.findById(id) == null) throw new SourceNotFoundException("Source do not found: id= "+ id);
        projectRepository.deleteById(id);
    }

    @GetMapping(path = "/number")
    public Project findByNumber(@RequestParam(value = "number") String number){
        Project project = projectRepository.findByNumber(number);
        if(project == null) return null;
        return project;
    }

    @GetMapping(path = "/{id}")
    public Project findById(@PathVariable("id") long id){
        Project project = projectRepository.findById(id);
        if(project == null) throw new SourceNotFoundException("Source do not found: id= "+ id);
        return project;
    }

    @PutMapping(path = "/{id}", consumes = "application/json")
    public Project updateProject(@PathVariable("id") long id, @Valid @RequestBody Project project) {
        Project projectUpdated = projectRepository.findById(id);
        if(projectUpdated == null) throw new SourceNotFoundException("Source do not found: id= "+ id);
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
