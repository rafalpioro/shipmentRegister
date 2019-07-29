package pl.pioro.shipmentregister.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.pioro.shipmentregister.entity.Incoterms;
import pl.pioro.shipmentregister.exception.SourceNotFoundException;
import pl.pioro.shipmentregister.repository.IncotermsRepository;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@Transactional
@CrossOrigin
@RequestMapping(path = "/admin/incoterms", produces = "application/json")
public class IncotermsController {

    @Autowired
    private IncotermsRepository incotermsRepository;

    @GetMapping
    public Iterable<Incoterms> findAll() {
        return incotermsRepository.findAll();
    }

    @PostMapping(consumes = "application/json")
    @ResponseStatus(value = HttpStatus.CREATED)
    public Incoterms create(@Valid @RequestBody Incoterms incoterms){
        return incotermsRepository.save(incoterms);
    }

    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable("id") int id){
        if(incotermsRepository.findById(id) == null) throw new SourceNotFoundException("Source do not found: id= "+ id);
        incotermsRepository.deleteById(id);
    }

    @GetMapping(path = "/{id}")
    public Incoterms findById(@PathVariable("id") int id){
        Incoterms incoterms = incotermsRepository.findById(id);
        if(incoterms == null) throw new SourceNotFoundException("Source do not found: id= "+ id);
        return incoterms;
    }

    @GetMapping(path = "/name")
    public Incoterms findByName(@RequestParam(value = "name") String name){
        Incoterms incoterms = incotermsRepository.findByName(name);
        if(incoterms == null) return null;
        return incoterms;
    }

    @PutMapping(path = "/{id}", consumes = "application/json")
    public Incoterms updateCarrier(@PathVariable("id") int id, @Valid @RequestBody Incoterms incoterms) {
        Incoterms incotermsUpdated = incotermsRepository.findById(id);
        if(incotermsUpdated == null) throw new SourceNotFoundException("Source do not found: id= "+ id);
        incotermsUpdated.setName(incoterms.getName());

        return this.incotermsRepository.save(incotermsUpdated);
    }
}
