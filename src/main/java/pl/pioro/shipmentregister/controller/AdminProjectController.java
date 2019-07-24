package pl.pioro.shipmentregister.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;
import pl.pioro.shipmentregister.entity.Project;
import pl.pioro.shipmentregister.repository.ProjectRepository;

import javax.transaction.Transactional;

@RestController
@Transactional
@CrossOrigin
@RequestMapping(path = "/admin-all", produces = "application/json")
public class AdminProjectController {

    @Autowired
    private ProjectRepository projectRepository;

    @GetMapping("/projects")
    public Iterable<Project> findAllProjects(@RequestParam(value = "page", required = false) String page, @RequestParam(value = "size", required = false) String size) {
        if(page != null && size != null) {
            PageRequest pageRequest = PageRequest.of(Integer.parseInt(page), Integer.parseInt(size));
            return projectRepository.findAll(pageRequest);
        } else {
            return projectRepository.findAll();
        }
    }
}
